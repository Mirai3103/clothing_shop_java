package com.shop.clothing.stockReceipt.entity;

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

public class Supplier {
    @Id
    private int supplierId;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String description;
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "supplier")
    private java.util.List<StockReceipt> stockReceipts;
}
