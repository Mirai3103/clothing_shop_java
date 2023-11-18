package com.shop.clothing.stockReceipt.repository;

import com.shop.clothing.stockReceipt.entity.StockReceipt;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface StockReceiptRepository extends JpaRepository<StockReceipt, Integer> {
    @Query(value = "SELECT DATE(o.created_date) AS date, CAST(COUNT(o.stock_receipt_id) as UNSIGNED) AS totalOrder, CAST(SUM(o.total) as UNSIGNED )AS totalRevenue FROM stock_receipt o WHERE o.created_date >= ?1 AND o.created_date <= ?2 GROUP BY DATE(o.created_date) ORDER BY DATE(o.created_date) ASC", nativeQuery = true)
    List<Tuple> getImportReport(Date startDate, Date endDate);
}
