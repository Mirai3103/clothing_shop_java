package com.shop.clothing.order.command.createOrder;

import com.shop.clothing.cart.CartRepository;
import com.shop.clothing.common.BusinessLogicException;
import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.common.util.ClientUtil;
import com.shop.clothing.config.ICurrentUserService;
import com.shop.clothing.delivery.query.getDeliveryFee.GetDeliveryFeeQuery;
import com.shop.clothing.order.entity.Order;
import com.shop.clothing.order.entity.OrderItem;
import com.shop.clothing.order.repository.OrderItemRepository;
import com.shop.clothing.order.repository.OrderRepository;
import com.shop.clothing.product.entity.ProductOption;
import com.shop.clothing.product.repository.ProductOptionRepository;
import com.shop.clothing.promotion.Promotion;
import com.shop.clothing.promotion.repository.PromotionRepository;
import com.shop.clothing.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CreateOrderCommandHandler implements IRequestHandler<CreateOrderCommand, String> {
    private final ICurrentUserService currentUserService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PromotionRepository promotionRepository;
    private final ISender sender;
    private final ProductOptionRepository productOptionRepository;
    private final ClientUtil clientUtil;

    @Override
    @Transactional(rollbackFor = {BusinessLogicException.class, Throwable.class})
    public HandleResponse<String> handle(CreateOrderCommand createOrderCommand) {
        var currentUserId = currentUserService.getCurrentUserId();
        if (currentUserId.isEmpty()) {
            return HandleResponse.error("Bạn chưa đăng nhập");
        }
        var productOptionsThatWillBuy = productOptionRepository.findAllByProductOptionIdIn(createOrderCommand.getOrderItems().stream().map(CreateOrderCommand.OrderItem::getProductOptionId).toList());
        Promotion promotion = null;
        if (createOrderCommand.getPromotionCode() != null) {
            promotion = promotionRepository.findByCodeIgnoreCase(createOrderCommand.getPromotionCode()).orElse(null);
        }
        Map<Integer, Integer> productOptionIdToQuantity = createOrderCommand.getOrderItems().stream().collect(
                java.util.stream.Collectors.toMap(CreateOrderCommand.OrderItem::getProductOptionId, CreateOrderCommand.OrderItem::getQuantity));
        var totalPrice = productOptionsThatWillBuy.stream().mapToDouble(productOption -> productOption.getProduct().getPrice() * productOptionIdToQuantity.get(productOption.getProductOptionId()) *
                ((double) (100 - productOption.getProduct().getDiscount()) / 100)
        ).sum();

        var address = clientUtil.from(createOrderCommand.getAddress());
        int fee = 0;
        var deliveryFee = sender.send(GetDeliveryFeeQuery.builder().totalPrice((int) totalPrice).toWard(address.ward).toDistrict(address.district).toProvince(address.city).build());
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
                .paymentMethod(createOrderCommand.getPaymentMethod())
                .build();
        orderRepository.save(newOrder);
        productOptionIdToQuantity.forEach((productOptionId, quantity) -> {
            var orderItem = OrderItem.builder()
                    .orderId(newOrder.getOrderId())
                    .productOptionId(productOptionId)
                    .quantity(quantity)
                    .build();

            orderItemRepository.save(orderItem);
            var productOption = productOptionRepository.findById(productOptionId).orElseThrow();
            if (productOption.getStock() < quantity) {
                // rollback
                throw new BusinessLogicException("Số lượng sản phẩm" + productOption.getProduct().getName() + " không đủ");
            }
            productOption.setStock(productOption.getStock() - quantity);
            productOptionRepository.save(productOption);
        });
        return HandleResponse.ok(newOrder.getOrderId());
    }
}
