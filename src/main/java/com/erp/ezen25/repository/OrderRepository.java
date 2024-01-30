package com.erp.ezen25.repository;

import com.erp.ezen25.dto.OrderListDTO;
import com.erp.ezen25.dto.WithdrawalDTO;
import com.erp.ezen25.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT new com.erp.ezen25.dto.WithdrawalDTO(m.name, o.orderCode, pi.productId, pi.image, pi.productName, pi.mCategory, pi.sCategory, o.orderNum, o.orderStatus, ps.productNum) " +
            "FROM Order o " +
            "JOIN Product_Info pi ON o.product.productId = pi.productId " +
            "JOIN Product_Stock ps ON o.product.productId = ps.product.productId " +
            "JOIN Member m ON m.memberId = o.member.memberId " +
            "WHERE o.orderCode = :orderCode")
    List<WithdrawalDTO> getWithdrawalDTO(@Param("orderCode") String orderCode);

    @Query("SELECT NEW com.erp.ezen25.dto.OrderListDTO(m.name, o.orderCode, o.orderDate, SUM(o.orderStatus) AS orderStatusSum, COUNT(o.orderStatus) AS orderStatusCnt) " +
            "FROM Order o JOIN o.member m " +
            "GROUP BY m.name, o.orderCode, o.orderDate")
    List<OrderListDTO> getOrderListDTO();
}
