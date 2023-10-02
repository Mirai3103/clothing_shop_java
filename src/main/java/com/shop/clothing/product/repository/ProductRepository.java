package com.shop.clothing.product.repository;

import com.shop.clothing.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer> {
    // has id in array
    List<Product> findByProductIdIn(Collection<Integer> productId);
}
