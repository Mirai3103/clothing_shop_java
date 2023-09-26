package com.shop.clothing.entity;


import com.shop.clothing.common.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductOptionImage extends AuditableEntity{
    @Id
    @Column(updatable = false, nullable = false,name = "url",length = 500)
    private String url;


    @ManyToOne(fetch = FetchType.LAZY)
    private ProductOption productOption;

}
