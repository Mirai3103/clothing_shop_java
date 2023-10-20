package com.shop.clothing.product.repository;

import com.shop.clothing.product.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Integer> {


    @Query(value = "select * from product_option po where po.product_product_id = ?1 and po.color_color_id = ?2 and po.size = ?3 order by po.color_color_id desc limit 1", nativeQuery = true)
    Optional<ProductOption> findFirstByProductIdAndColorIdAndSize(Integer productId, Integer colorId, String size);

    @Query(value = "select * from product_option po where po.product_product_id = ?1 and po.color_color_id = ?2 order by po.created_date desc limit 1", nativeQuery = true)
    Optional<ProductOption> findFirstByProductIdAndColorId(Integer productId, Integer colorId);

    @Query(value = "DELETE FROM  product_option p WHERE p.product_option_id = ?1", nativeQuery = true)
    @Modifying()
    void hardDeleteById(int id);

    List<ProductOption> findAllByProductOptionIdIn(List<Integer> ids);
}
