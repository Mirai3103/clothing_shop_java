package com.shop.clothing.repository;

import com.shop.clothing.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
    @Query("SELECT p FROM Permission p WHERE p.normalizedName = ?1")
    Permission findByName(String name);
}
