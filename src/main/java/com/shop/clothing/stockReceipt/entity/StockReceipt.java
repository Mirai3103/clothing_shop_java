package com.shop.clothing.stockReceipt.entity;

import com.shop.clothing.common.AuditableEntity;
import com.shop.clothing.order.entity.OrderItem;
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
@SQLDelete(sql = "UPDATE stock_receipt SET deleted_date = NOW() WHERE stock_receipt_id=?")
@Where(clause = "deleted_date is null")
public class StockReceipt extends AuditableEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false,name = "stock_receipt_id")
    private int stockReceiptId;

    @Column(nullable = false)
    private int total = 0;

    @Column(length = 100)
    private String note;

    @Column(nullable = false)
    private int supplierId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Supplier supplier;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_receipt_id")
    private java.util.List<StockReceiptItem> stockReceiptItems;



}