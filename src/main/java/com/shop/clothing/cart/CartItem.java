package com.shop.clothing.cart;

import com.shop.clothing.product.entity.ProductOption;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table()
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@IdClass(CartItem.CartItemKey.class)
public class CartItem {
    @AllArgsConstructor
    @NoArgsConstructor

    static
    class CartItemKey implements java.io.Serializable{
        private String userId;
        private int productOptionId;
    }
    @Id
    @Column(updatable = false, nullable = false,name = "user_id")
    private String userId;
    @Id
    @Column(updatable = false, nullable = false,name = "product_option_id")
    private int productOptionId;
    private int quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_option_id", insertable = false, updatable = false)
    private ProductOption productOption;
}

