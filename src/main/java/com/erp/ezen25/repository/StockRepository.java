package com.erp.ezen25.repository;

import com.erp.ezen25.dto.ExportDTO;
import com.erp.ezen25.entity.Product_Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface StockRepository extends JpaRepository<Product_Stock, Long> {

    @Query("select ps, i.importDate from Product_Stock ps left join Import i on i.product = ps.product")
    List<Object[]> getImportDateWithImport();

    @Query("SELECT new com.erp.ezen25.dto.ExportDTO(e.exportId, pi.productId, pi.image, pi.productName, pi.mCategory, pi.sCategory, o.orderNum, ps.productNum, pi.sellPrice, e.exportNum, o.orderStatus, e.exportDate, e.orderCode) " +
            "FROM Export e " +
            "JOIN e.productId pi " +
            "JOIN Product_Stock ps ON pi.productId = ps.product.productId " +
            "JOIN Order o ON e.orderCode = o.orderCode " +
            "WHERE e.orderCode = :orderCode AND pi.productId IN :productIds")
    List<ExportDTO> getListForExportByOrderCodeAndProductIds(@Param("orderCode") String orderCode, @Param("productIds") List<Long> productIds);

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.orderStatus = '1' WHERE o.orderCode = :orderCode AND o.product.productId IN :productIds")
    void updateOrderStatus(String orderCode, List<Long> productIds);

}
