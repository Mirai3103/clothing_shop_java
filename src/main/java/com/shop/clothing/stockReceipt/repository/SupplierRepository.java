package com.shop.clothing.stockReceipt.repository;

import com.shop.clothing.stockReceipt.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
}
