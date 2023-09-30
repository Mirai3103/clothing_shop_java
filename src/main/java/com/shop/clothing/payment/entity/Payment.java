package com.shop.clothing.payment.entity;

import com.shop.clothing.common.AuditableEntity;
import com.shop.clothing.payment.entity.enums.PaymentMethod;
import com.shop.clothing.payment.entity.enums.PaymentStatus;
import com.shop.clothing.order.entity.Order;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
public class Payment extends AuditableEntity{
    @Id
   @Column(updatable = false, nullable = false,name = "payment_id",length = 36)
    private String paymentId;
    @Column(nullable = false)
    private PaymentMethod paymentMethod;
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;
    @Column(nullable = false)
    private String transactionId;
    @Column(nullable = false)
    private String paymentDetails;
    @Column()
    private String paymentResponse;
    private float amount;

  @ManyToOne(fetch = FetchType.LAZY)
 private Order order;

}
