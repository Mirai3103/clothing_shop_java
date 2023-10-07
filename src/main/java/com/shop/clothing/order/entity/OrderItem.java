package com.shop.clothing.order.entity;

import com.shop.clothing.product.entity.ProductOption;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table()
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@IdClass(OrderItem.OrderItemKey.class)
public class OrderItem {
    @AllArgsConstructor
    @NoArgsConstructor
  public   static
    class OrderItemKey implements java.io.Serializable{
        private String orderId;
        private int productOptionId;
    }

    @Id
    @Column(updatable = false, nullable = false,name = "order_id")
    private String orderId;
    @Id
    @Column(updatable = false, nullable = false,name = "product_option_id")
    private int productOptionId;
    private int quantity;
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_option_id", insertable = false, updatable = false)
    private ProductOption productOption;

}
