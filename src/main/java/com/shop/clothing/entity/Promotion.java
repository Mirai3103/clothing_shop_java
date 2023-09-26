package com.shop.clothing.entity;

import com.shop.clothing.common.AuditableEntity;
import com.shop.clothing.entity.enums.PromotionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
@SQLDelete(sql = "UPDATE promotion SET deleted_date = NOW() WHERE brand_id=?")
@Where(clause = "deleted_date is null")
public class Promotion extends AuditableEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false,name = "promotion_id")
    private int promotionId;

    @Column(unique = true, nullable = false,length = 100)
    private String code;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private int discount;

    @Column(nullable = false)
    private PromotionType type;

    @Column()
    private int minOrderAmount;

    @Column()
    private int maxValue;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private int stock;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "promotion")
    private java.util.List<Order> orders;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_date")
    private LocalDateTime deletedDate = null;
}