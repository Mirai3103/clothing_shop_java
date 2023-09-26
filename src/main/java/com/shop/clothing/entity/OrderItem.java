package com.shop.clothing.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table()
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@IdClass(OrderItemKey.class)
public class OrderItem {
    @Id
    @Column(updatable = false, nullable = false,name = "order_id")
    private String orderId;
    @Id
    @Column(updatable = false, nullable = false,name = "product_option_id")
    private int productOptionId;
    private int quantity;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_id", insertable = false, updatable = false)
    private ProductOption productOption;
}
@AllArgsConstructor
@NoArgsConstructor
class OrderItemKey implements java.io.Serializable{
    private String orderId;
    private int productOptionId;

}
