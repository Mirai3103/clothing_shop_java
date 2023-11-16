package com.shop.clothing.product.repository;

import com.shop.clothing.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying

    @Query(value = "delete from Product p where p.product_id = ?1", nativeQuery = true)
    void hardDeleteById(int id);

    @Query(value = "SELECT DISTINCT  p.product  FROM ProductOption p WHERE " +
            " (:keyword IS NULL OR p.product.name LIKE %:keyword%)" +
            "AND  (:categoryIds IS NULL   or p.product.category.categoryId IN :categoryIds)" +
            " AND (:forGenders IS NULL   or p.product.forGender IN :forGenders)" +
            " AND (p.product.price >= :minPrice)" +
            " AND ( p.product.price <= :maxPrice)" +
            " AND (p.deletedDate IS NULL)" +
            " AND (:colorIds IS NULL   or p.color.colorId IN :colorIds)" +
            " AND (:sizes IS NULL   or p.size IN :sizes)",
            countQuery = "SELECT count(DISTINCT p.product.productId) FROM ProductOption p WHERE " +
                    " (:keyword IS NULL OR p.product.name LIKE %:keyword%)" +
                    "AND  (:categoryIds IS NULL   or p.product.category.categoryId IN :categoryIds)" +
                    " AND (:forGenders IS NULL   or p.product.forGender IN :forGenders)" +
                    " AND (p.product.price >= :minPrice)" +
                    " AND ( p.product.price <= :maxPrice)" +
                    " AND (p.deletedDate IS NULL)" +
                    " AND (:colorIds IS NULL   or p.color.colorId IN :colorIds)" +
                    " AND (:sizes IS NULL   or p.size IN :sizes)"

    )
    Page<Product> searchAllProducts(String keyword, int[] categoryIds, Product.ProductGender[] forGenders,
                                    int minPrice, int maxPrice,
                                    int[] colorIds, String[] sizes,
                                    Pageable pageable);

    @Modifying
    @Query("update Product p set p.deletedDate = null where p.productId = ?1")
    void recoveryByProductId(int productId);
}
