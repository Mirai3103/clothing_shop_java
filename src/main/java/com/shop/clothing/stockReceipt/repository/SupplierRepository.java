package com.shop.clothing.stockReceipt.repository;

import com.shop.clothing.stockReceipt.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {

    @Query(value = "DELETE  FROM Supplier s where s.supplierId = ?1")
    @Modifying
    void hardDeleteById(int supplierId);
}
