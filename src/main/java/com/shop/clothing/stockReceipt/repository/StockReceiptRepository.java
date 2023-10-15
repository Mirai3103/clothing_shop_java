package com.shop.clothing.stockReceipt.repository;

import com.shop.clothing.stockReceipt.entity.StockReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockReceiptRepository extends JpaRepository<StockReceipt, Integer> {
}
