package com.shop.clothing.scheduleTask;

import com.shop.clothing.common.Cqrs.ISender;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PaymentTask {
    private final ISender sender;

    // 15 minutes
    @Scheduled(fixedRate = 900000)
    public void removeExpiredPayment() {
        System.out.println("Huỷ các đơn hàng hết hạn thanh toán");
        sender.send(new com.shop.clothing.payment.command.cancelExpiredPayment.CancelExpiredPaymentCommand());

    }
}
