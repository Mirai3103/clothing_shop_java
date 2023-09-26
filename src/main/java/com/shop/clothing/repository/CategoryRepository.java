package com.shop.clothing.repository;

import com.shop.clothing.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<Category> findAllByParentCategoryId(Integer parentCategoryId, org.springframework.data.domain.Pageable pageable);
    Page<Category> findAllByParentCategoryIdIsNull(org.springframework.data.domain.Pageable pageable);
    Page<Category> findAllByParentCategoryIdIsNotNull(org.springframework.data.domain.Pageable pageable);
    Page<Category> findAllByNameContaining(String keyword, org.springframework.data.domain.Pageable pageable);
    Optional<Category> findBySlug(String slug);
    Optional<Category> findByName(String name);

}