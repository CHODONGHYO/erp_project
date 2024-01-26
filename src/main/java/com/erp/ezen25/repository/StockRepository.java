package com.erp.ezen25.repository;

import com.erp.ezen25.dto.ExportDTO;
import com.erp.ezen25.entity.Product_Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface StockRepository extends JpaRepository<Product_Stock, Long> {

    @Query("select ps, i.importDate from Product_Stock ps left join Import i on i.product = ps.product")
    List<Object[]> getImportDateWithImport();
/*
    @Query("SELECT DISTINCT pi.image, pi.product_id, pi.product_name, pi.m_category, pi.s_category, ps.product_num, e.export_num, pi.sell_price, o.order_status " +
            "FROM ProductInfo pi " +
            "JOIN ProductStock ps ON pi.product_id = ps.product_id " +
            "JOIN Export e ON pi.product_id = e.product_id " +
            "JOIN Ordering o ON e.orderCode = o.orderCode " +
            "WHERE e.orderCode = :orderCode AND pi.product_id IN :productIds")
    List<ExportDTO> getListForExportByOrderCodeAndProductIds(String orderCode, List<Long> productIds);
*/


    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.orderStatus = '1' WHERE o.orderCode = :orderCode AND o.product.productId IN :productIds")
    void updateOrderStatus(String orderCode, List<Long> productIds);

}
