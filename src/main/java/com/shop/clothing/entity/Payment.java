package com.shop.clothing.entity;

import com.shop.clothing.common.AuditableEntity;
import com.shop.clothing.entity.enums.PaymentMethod;
import com.shop.clothing.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
