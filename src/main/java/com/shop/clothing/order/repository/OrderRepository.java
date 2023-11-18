package com.shop.clothing.order.repository;

import com.shop.clothing.order.entity.Order;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByUserUserId(String userId);
    //    SELECT
//    DATE(o.completed_date) AS date,
//    COUNT(o.order_id) AS totalOrder,
//    SUM(o.total_amount) AS totalRevenue
//    FROM _order o
//    WHERE o.completed_date is not null
//    AND o.completed_date >= '2019-01-01'
//    AND o.completed_date <= '2019-12-31'
//    GROUP BY DATE(o.completed_date)
//    ORDER BY DATE(o.completed_date) ASC;
    @Query(value = "SELECT DATE(o.completed_date) AS date, CAST(COUNT(o.order_id) as UNSIGNED) AS totalOrder, CAST(SUM(o.total_amount) as UNSIGNED )AS totalRevenue FROM _order o WHERE o.completed_date is not null AND o.completed_date >= ?1 AND o.completed_date <= ?2 GROUP BY DATE(o.completed_date) ORDER BY DATE(o.completed_date) ASC", nativeQuery = true)
    // hibernate query
    List<Tuple> getSoldReport(Date startDate, Date endDate);

}
