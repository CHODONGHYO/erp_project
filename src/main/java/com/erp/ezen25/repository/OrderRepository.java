    package com.erp.ezen25.repository;


    import com.erp.ezen25.entity.Export;
    import com.erp.ezen25.entity.Order;
    import com.querydsl.core.BooleanBuilder;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.querydsl.QuerydslPredicateExecutor;
    import org.springframework.data.repository.query.Param;

    import java.util.List;
    import java.util.Optional;

    public interface OrderRepository extends JpaRepository<Order, Long> , QuerydslPredicateExecutor<Order> {
        @Query("SELECT o FROM Order o JOIN FETCH o.member WHERE o.orderCode = :orderCode")
        List<Order> findByOrderCode(String orderCode);
        @Query("SELECT o FROM Order o WHERE o.orderId= :orderId")
        Optional<Order> findByOrderId(@Param("orderId") Long orderId);


        @Query(value = "select o.order_id, o.member_id, o.order_date, o.product_id, o.order_num, o.order_description, o.order_status, o.order_code, m.name " +
                "from ordering o, member m where o.member_id = m.member_id and o.member_id = :memberId", nativeQuery = true)
        List<Order> getListByMemberId(@Param("memberId") Long memberId);
        @Query(value = "select o.order_id, o.member_id, o.order_date, o.product_id, o.order_num, o.order_description, o.order_status, o.order_code, m.name " +
                "from ordering o, member m where o.member_id = m.member_id and o.order_code = :orderCode ", nativeQuery = true)
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



    }
