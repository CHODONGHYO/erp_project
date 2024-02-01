package com.erp.ezen25.repository;

import com.erp.ezen25.dto.ExportCompleteDTO;
import com.erp.ezen25.dto.ExportDTO;
import com.erp.ezen25.dto.StockDTO;
import com.erp.ezen25.entity.Product_Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface StockRepository extends JpaRepository<Product_Stock, Long> {

    @Query("select new com.erp.ezen25.dto.StockDTO(ps.pNumId, pi.image, pi.productId, ps.productNum, pi.productName, pi.mCategory, pi.sCategory, pi.originalPrice, pi.sellPrice) from Product_Stock ps left join Product_Info pi on pi.productId = ps.product.productId")
    List<StockDTO> getImportDateWithImport();

    @Query("SELECT new com.erp.ezen25.dto.ExportDTO(e.exportId, pi.productId, pi.image, pi.productName, pi.mCategory, pi.sCategory, o.orderNum, ps.productNum, pi.sellPrice, e.exportNum, o.orderStatus, e.exportDate, e.orderCode) " +
            "FROM Export e " +
            "JOIN Product_Info pi ON e.productId = pi " +
            "JOIN Product_Stock ps ON pi.productId = ps.product.productId " +
            "JOIN Order o ON e.orderCode = o.orderCode AND pi.productId = o.product.productId " +
            "WHERE e.orderCode = :orderCode AND o.orderStatus = '1'")
    List<ExportDTO> getListForExportByOrderCodeAndProductIds(@Param("orderCode") String orderCode);

    @Query("SELECT new com.erp.ezen25.dto.ExportCompleteDTO(m.name, o.orderCode, pi.productId, pi.productName, ps.productNum, e.exportNum, e.exportDate) " +
            "FROM Export e " +
            "JOIN Order o ON e.orderCode = o.orderCode AND e.productId.productId = o.product.productId " +
            "JOIN Product_Info pi ON o.product.productId = pi.productId " +
            "JOIN Product_Stock ps ON pi.productId = ps.product.productId " +
            "JOIN Member m ON o.member.memberId = m.memberId " +
            "ORDER BY e.exportDate DESC")
    List<ExportCompleteDTO> getCompletedExportList();
    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.orderStatus = '1' WHERE o.orderCode = :orderCode AND o.product.productId IN :productIds")
    void updateOrderStatus(@Param("orderCode") String orderCode, List<Long> productIds);

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.orderStatus = '2' WHERE o.orderCode = :orderCode")
    void updateOrderStatus2(@Param("orderCode") String orderCode);

/*
    @Modifying
    @Transactional
    @Query("UPDATE Product_Stock ps SET ps.productNum = ps.productNum - o.orderNum " +
            "WHERE EXISTS (SELECT 1 FROM Order o WHERE o.product.productId = ps.product.productId AND o.orderCode = :orderCode)")
    void updateProductNum(@Param("orderCode") String orderCode);
*/
}
