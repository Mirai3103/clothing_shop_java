package com.shop.clothing.product.entity;

import com.shop.clothing.common.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE product_option SET deleted_date = NOW() WHERE  product_option_id=?")
@Where(clause = "deleted_date is null")
@Table(name = "product_option")
public class ProductOption extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, name = "product_option_id")
    private int productOptionId;


    @Column(nullable = false, name = "color", length = 50)
    private String color;


    @Column(nullable = false, name = "size", length = 50)
    private String size;


    private int stock = 0;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_date")
    private LocalDateTime deletedDate = null;


    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;


}