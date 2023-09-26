package com.shop.clothing.entity;

import com.shop.clothing.common.AuditableEntity;
import com.shop.clothing.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "0rder")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Order extends AuditableEntity {
    @Id
    @Column(updatable = false, nullable = false, name = "order_id", length = 36)
    private String orderId;

    @Column(name = "user_id", length = 36)
    private String userId;

    @Column(name = "customer_name", length = 100)
    private String customerName;

    @Column(length = 500)
    private String address;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "total_amount")
    private int totalAmount;

    @Column(length = 500)
    private String note;

    @Column(name = "delivery_fee")
    private double deliveryFee;

    @Column(name = "cancel_reason", length = 500)
    private String cancelReason;



    @Column()
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private java.util.List<Payment> payments;

    @ManyToOne(fetch = FetchType.EAGER)
    private Promotion promotion;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private java.util.List<OrderItem> orderItems;
}
