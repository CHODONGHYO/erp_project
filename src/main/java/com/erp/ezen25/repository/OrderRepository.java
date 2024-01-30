    package com.erp.ezen25.repository;


    import com.erp.ezen25.dto.OrderListDTO;
    import com.erp.ezen25.dto.WithdrawalDTO;
    import com.erp.ezen25.entity.Export;
    import com.erp.ezen25.entity.Order;
    import com.querydsl.core.BooleanBuilder;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.querydsl.QuerydslPredicateExecutor;
    import org.springframework.data.repository.query.Param;
    import com.erp.ezen25.queryMapping.OrderAndStockMapping;
    import org.springframework.web.bind.annotation.RequestParam;

    import java.util.List;
    import java.util.Optional;

    public interface OrderRepository extends JpaRepository<Order, Long> , QuerydslPredicateExecutor<Order> {
        @Query("SELECT o FROM Order o JOIN FETCH o.member WHERE o.orderCode = :orderCode")
        List<Order> findByOrderCode(String orderCode);
        @Query("SELECT o FROM Order o WHERE o.orderId= :orderId")
        Optional<Order> findByOrderId(@Param("orderId") Long orderId);

        @Query("SELECT o FROM Order o JOIN FETCH o.member WHERE o.orderCode = :orderCode")
        Optional<Order> getByOrderCode(@Param("orderCode") String orderCode);

        @Query(value = "select o.order_id, o.member_id, o.order_date, o.product_id, o.order_num, o.order_description, o.order_status, o.order_code, m.name" +
                " from ordering o, member m where o.member_id = m.member_id and o.order_code= :orderCode LIMIT 1",nativeQuery = true)
        Optional<Order> getOneByOrderCode(@Param("orderCode") String orderCode);

        @Query(value = "select o.order_id, o.member_id, o.order_date, o.product_id, o.order_num, o.order_description, o.order_status, o.order_code, m.name " +
                "from ordering o, member m where o.member_id = m.member_id and o.member_id = :memberId", nativeQuery = true)
        List<Order> getListByMemberId(@Param("memberId") Long memberId);
        @Query(value = "select o.order_id, o.member_id, o.order_date, o.product_id, o.order_num, o.order_description, o.order_status, o.order_code, m.name " +
                "from ordering o, member m where o.member_id = m.member_id and o.order_code = :orderCode", nativeQuery = true)
        List<Order> getListByOrderCode(@Param("orderCode") String orderCode);

        @Query(value = "SELECT p.product_id, p.product_name, p.product_description, p.brand_id, p.m_category, p.s_category, p.original_price, p.sell_price, p.image, " +
                "o.order_id, o.member_id, o.order_date, o.order_num, o.order_description, o.order_out_date, o.order_status, o.order_code " +
                "FROM product_info p, ordering o " +
                "WHERE p.product_id = o.product_id", nativeQuery = true)
        List<Object[]> joinOrderAndProduct();

        @Query(value = "SELECT s_category FROM product_info WHERE m_category = :upperCategory GROUP BY s_category", nativeQuery = true)
        List<String> getSCategoryList(@Param("upperCategory") String upperCategory);

        @Query(value = "SELECT m_category from product_info group by m_category",nativeQuery = true)
        List<String> findMCategoryList();

        @Query(value = "SELECT product_id from product_info where s_category= :subcategory",nativeQuery = true)
        List<String> findProductList(@Param("subcategory") String subcategory);



        @Query(value = "select s.product_name, s.order_num, product_num from\n" +
                "(select product_name, sum(order_num) as order_num, p.product_id\n" +
                "\tfrom ordering p\n" +
                "    left join product_info pi on p.product_id = pi.product_id\n" +
                "    where p.order_status < 1 and p.order_date > date(now())\n" +
                "    group by p.product_id) s\n" +
                "    left join product_stock ps on s.product_id = ps.product_id" ,nativeQuery = true)
        List<OrderAndStockMapping> findOrderAndStockList();



    }


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

