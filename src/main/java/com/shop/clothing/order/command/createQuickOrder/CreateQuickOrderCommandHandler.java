package com.shop.clothing.order.command.createQuickOrder;

import com.shop.clothing.cart.CartRepository;
import com.shop.clothing.common.BusinessLogicException;
import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.common.validation.NullOrNotBlank;
import com.shop.clothing.config.ICurrentUserService;
import com.shop.clothing.delivery.query.getDeliveryFee.GetDeliveryFeeQuery;
import com.shop.clothing.mail.MailService;
import com.shop.clothing.order.command.createOrder.CreateOrderCommand;
import com.shop.clothing.order.entity.Order;
import com.shop.clothing.order.entity.OrderItem;
import com.shop.clothing.order.repository.OrderItemRepository;
import com.shop.clothing.order.repository.OrderRepository;
import com.shop.clothing.payment.entity.enums.PaymentMethod;
import com.shop.clothing.product.repository.ProductOptionRepository;
import com.shop.clothing.promotion.Promotion;
import com.shop.clothing.promotion.repository.PromotionRepository;
import com.shop.clothing.user.entity.User;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Service
public class CreateQuickOrderCommandHandler implements IRequestHandler<CreateQuickOrderCommand, String> {

    private final ICurrentUserService currentUserService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PromotionRepository promotionRepository;
    private final ISender sender;
    private final ProductOptionRepository productOptionRepository;
    private final MailService mailService;

    @Override
    @Transactional(rollbackFor = {BusinessLogicException.class, Throwable.class, ResponseStatusException.class})
    public HandleResponse<String> handle(CreateQuickOrderCommand command) {

        var productOption = productOptionRepository.findById(command.getProductOptionId());
        if (productOption.isEmpty()) {
            return HandleResponse.error("Không tìm thấy sản phẩm");
        }
        if (productOption.get().getStock() < command.getQuantity()) {
            return HandleResponse.error("Số lượng sản phẩm không đủ");
        }
        Promotion promotion = null;
        if (command.getPromotionCode() != null) {
            promotion = promotionRepository.findByCodeIgnoreCase(command.getPromotionCode()).orElse(null);
        }
        var totalPrice = productOption.get().getProduct().getPrice() * command.getQuantity() *
                ((double) (100 - productOption.get().getProduct().getDiscount()) / 100);


        var addressList = command.getAddress().split(",");
        var ward = addressList[addressList.length - 3].trim();
        var district = addressList[addressList.length - 2].trim();
        var city = addressList[addressList.length - 1].trim();
        int fee = 0;
        var deliveryFee = sender.send(GetDeliveryFeeQuery.builder().totalPrice((int) totalPrice).toWard(ward).toDistrict(district).toProvince(city).build());
        if (deliveryFee.isOk()) {
            fee = deliveryFee.get();
        }
        var newOrder = Order.builder().orderId(UUID.randomUUID().toString())
                .user((User) currentUserService.getCurrentUser().orElse(null))
                .address(command.getAddress())
                .customerName(command.getCustomerName())
                .phoneNumber(command.getPhoneNumber())
                .note(command.getNote())
                .email(command.getEmail())
                .user((User) currentUserService.getCurrentUser().orElseThrow())
                .promotion(promotion)
                .payments(null)
                .deliveryFee(fee)
                .totalAmount((int) totalPrice + fee)
                .paymentMethod(command.getPaymentMethod())
                .build();
        orderRepository.save(newOrder);
        var orderItem = OrderItem.builder()
                .orderId(newOrder.getOrderId())
                .productOptionId(command.getProductOptionId())
                .quantity(command.getQuantity())
                .price(productOption.get().getProduct().getPrice() * command.getQuantity() *
                        ((double) (100 - productOption.get().getProduct().getDiscount()) / 100))
                .build();
        orderItemRepository.save(orderItem);

        productOption.get().setStock(productOption.get().getStock() - command.getQuantity());
        productOptionRepository.save(productOption.get());

        CompletableFuture.runAsync(() -> {
            try {
                mailService.sendEmail(newOrder.getEmail(), "Đặt hàng thành công", "Đơn hàng <b>" + newOrder.getOrderId() + "</b> của bạn đã được đặt thành công");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
        return HandleResponse.ok(newOrder.getOrderId());
    }
}
