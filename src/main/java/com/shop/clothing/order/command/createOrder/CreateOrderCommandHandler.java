package com.shop.clothing.order.command.createOrder;

import com.shop.clothing.cart.CartRepository;
import com.shop.clothing.common.BusinessLogicException;
import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.config.ICurrentUserService;
import com.shop.clothing.delivery.query.getDeliveryFee.GetDeliveryFeeQuery;
import com.shop.clothing.order.entity.Order;
import com.shop.clothing.order.entity.OrderItem;
import com.shop.clothing.order.repository.OrderItemRepository;
import com.shop.clothing.order.repository.OrderRepository;
import com.shop.clothing.product.repository.ProductOptionRepository;
import com.shop.clothing.promotion.Promotion;
import com.shop.clothing.promotion.repository.PromotionRepository;
import com.shop.clothing.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CreateOrderCommandHandler implements IRequestHandler<CreateOrderCommand, String> {
    private final CartRepository cartRepository;
    private final ICurrentUserService currentUserService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PromotionRepository promotionRepository;
    private final ISender sender;
    private final ProductOptionRepository productOptionRepository;

    @Override
    @Transactional(rollbackFor = {BusinessLogicException.class, Throwable.class})
    public HandleResponse<String> handle(CreateOrderCommand createOrderCommand) {
        var currentUserId = currentUserService.getCurrentUserId();
        if (currentUserId.isEmpty()) {
            return HandleResponse.error("Bạn chưa đăng nhập");
        }
        var cartItems = createOrderCommand.getProductOptionIds().stream()
                .map(productOptionId -> cartRepository.findByUserIdAndProductOptionId(currentUserId.get(), productOptionId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        if (cartItems.isEmpty()) {
            return HandleResponse.error("Cần có sản phẩm trong giỏ hàng");
        }
        Promotion promotion = null;
        if (createOrderCommand.getPromotionCode() != null) {
            promotion = promotionRepository.findByCodeIgnoreCase(createOrderCommand.getPromotionCode()).orElse(null);
        }
        var totalPrice = cartItems.stream().mapToDouble(cartItem -> cartItem.getProductOption().getProduct().getPrice() * cartItem.getQuantity() *
                ((100 - cartItem.getProductOption().getProduct().getDiscount()) / 100)
        ).sum();

        var addressList = createOrderCommand.getAddress().split(",");
        var ward = addressList[addressList.length - 3].trim();
        var district = addressList[addressList.length - 2].trim();
        var city = addressList[addressList.length - 1].trim();
        int fee = 0;
        var deliveryFee = sender.send(GetDeliveryFeeQuery.builder().totalPrice((int) totalPrice).toWard(ward).toDistrict(district).toProvince(city).build());
        if (deliveryFee.isOk()) {
            fee = deliveryFee.get();
        }
        var newOrder = Order.builder().orderId(UUID.randomUUID().toString())
                .address(createOrderCommand.getAddress())
                .customerName(createOrderCommand.getCustomerName())
                .phoneNumber(createOrderCommand.getPhoneNumber())
                .note(createOrderCommand.getNote())
                .user((User) currentUserService.getCurrentUser().orElseThrow())
                .promotion(promotion)
                .payments(null)
                .deliveryFee(fee)
                .totalAmount((int) totalPrice + fee)
                .build();
        orderRepository.save(newOrder);
        cartItems.forEach(cartItem -> {
            var orderItem = OrderItem.builder()
                    .orderId(newOrder.getOrderId())
                    .productOptionId(cartItem.getProductOptionId())
                    .quantity(cartItem.getQuantity())
                    .build();
            orderItemRepository.save(orderItem);
            var productOption = cartItem.getProductOption();
            if (productOption.getStock() < cartItem.getQuantity()) {
                // rollback
                throw new BusinessLogicException("Số lượng sản phẩm" + productOption.getProduct().getName() + " không đủ");
            }
            productOption.setStock(productOption.getStock() - cartItem.getQuantity());
            productOptionRepository.save(productOption);
            cartRepository.delete(cartItem);
        });
        return HandleResponse.ok(newOrder.getOrderId());
    }
}
