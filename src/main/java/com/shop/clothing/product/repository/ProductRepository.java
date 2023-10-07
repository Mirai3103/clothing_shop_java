package com.shop.clothing.product.repository;

import com.shop.clothing.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // has id in array
    List<Product> findByProductIdIn(Collection<Integer> productId);

    Optional<Product> findBySlug(String slug);

    @Query(value = "delete from Product p where p.product_id = ?1", nativeQuery = true)
    void hardDeleteById(int id);

}
