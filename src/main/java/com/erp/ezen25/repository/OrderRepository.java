package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderCode(String orderCode);
}
