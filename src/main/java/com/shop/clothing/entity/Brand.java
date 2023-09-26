package com.shop.clothing.entity;

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
@Table
@SQLDelete(sql = "UPDATE brand SET deleted_date = NOW() WHERE brand_id=?")
@Where(clause = "deleted_date is null")
public class Brand extends AuditableEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false,name = "brand_id")
    private int brandId;


    private String name;


    @Column(nullable = true,length = 100)
    private String slug;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_date")
    private   LocalDateTime deletedDate = null;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "brand")
    private java.util.List<Product> products;

}
