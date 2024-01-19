package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o JOIN FETCH o.member WHERE o.orderCode = :orderCode")
    List<Order> findByOrderCode(String orderCode);
}
