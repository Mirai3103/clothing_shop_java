package com.shop.clothing.scheduleTask;

import com.shop.clothing.order.entity.OrderItem;
import com.shop.clothing.order.repository.OrderItemRepository;
import com.shop.clothing.order.repository.OrderRepository;
import com.shop.clothing.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
@AllArgsConstructor
public class ProductTask {
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

//    @Scheduled(cron = "0 0 1 * * ?")
//    public void calculateTotalSold() {
//        var orderItems = orderItemRepository.findAll();
//        for (OrderItem orderItem : orderItems) {
//            var product = orderItem.getProductOption().getProduct();
//            product.setTotalSold(product.getTotalSold() + orderItem.getQuantity());
//            productRepository.save(product);
//        }
//
//    }
//UPDATE huuhoang_clothing_shop.product p
//    INNER JOIN huuhoang_clothing_shop.product_option po ON po.product_product_id = p.product_id
//    INNER JOIN huuhoang_clothing_shop.order_item as oi ON oi.product_option_id = po.product_option_id
//    INNER JOIN `_order` o ON o.order_id = oi.order_id
//    SET p.total_sold = (
//    SELECT SUM(oi.quantity)
//    FROM order_item oi
//    INNER JOIN product_option po ON oi.product_option_id = po.product_option_id
//    INNER JOIN `_order` o ON o.order_id = oi.order_id
//    WHERE  po.product_product_id = p.product_id
//)
//    WHERE 1=1
}
