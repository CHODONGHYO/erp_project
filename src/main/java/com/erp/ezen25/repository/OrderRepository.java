package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Order;
import com.erp.ezen25.queryMapping.OrderAndStockMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o JOIN FETCH o.member WHERE o.orderCode = :orderCode")
    List<Order> findByOrderCode(String orderCode);

    @Query(value = "select s.product_name, s.order_num, product_num from\n" +
            "(select product_name, sum(order_num) as order_num, p.product_id\n" +
            "\tfrom ordering p\n" +
            "    left join product_info pi on p.product_id = pi.product_id\n" +
            "    where p.order_status < 1\n" +
            "    group by p.product_id) s\n" +
            "    left join product_stock ps on s.product_id = ps.product_id;", nativeQuery = true)
    List<OrderAndStockMapping> findOrderAndStockList();

}
