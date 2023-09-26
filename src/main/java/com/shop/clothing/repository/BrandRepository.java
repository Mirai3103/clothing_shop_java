package com.shop.clothing.repository;

import com.shop.clothing.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Optional<Brand> findByName(String name);
    Page<Brand> findAllByNameContaining(String name, org.springframework.data.domain.Pageable pageable);
}
