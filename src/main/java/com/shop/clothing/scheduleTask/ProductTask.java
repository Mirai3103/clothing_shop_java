package com.shop.clothing.scheduleTask;

import com.shop.clothing.order.entity.enums.OrderStatus;
import com.shop.clothing.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProductTask {
    private final ProductRepository productRepository;

    // every day at 3:00 AM
    @Scheduled(cron = "0 0 3 * * ?")
    public void updateProductTotalSold() {
        System.out.println("Cập nhật số lượng sản phẩm đã bán");
        productRepository.updateTotalSold(new OrderStatus[]{OrderStatus.DELIVERED, OrderStatus.SHIPPING});
        productRepository.updateTotalToZeroWhereSoldIsNull();

    }
}
