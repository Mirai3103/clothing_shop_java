package com.shop.clothing.payment.repository;

import com.shop.clothing.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    @Query(value = "select * from payment p where p.order_order_id = ?1 order by p.created_date desc limit 1", nativeQuery = true)

    Optional<Payment> findFirstByOrderIdSortedByCreatedDateDesc(String orderId);
}
