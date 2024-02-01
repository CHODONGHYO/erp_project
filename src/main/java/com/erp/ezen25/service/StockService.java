package com.erp.ezen25.service;

import com.erp.ezen25.dto.*;
import com.erp.ezen25.entity.Member;
import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.entity.Product_Stock;

import java.util.List;

public interface StockService {

    List<StockDTO> getListWithProduct();

    List<ExportDTO> getListForExportByOrderCodeAndProductIds(String orderCode);

    List<ExportCompleteDTO> getCompletedExportList();

    void updateOrderStatus(String orderCode, List<Long> productIds);
    void updateOrderStatus2(String orderCode);
    /*void updateProductNum(String orderCode);*/

    default Product_Stock dtoToEntity(StockDTO dto) {
        Product_Stock entity = Product_Stock.builder()
                .pNumId(dto.getPNumId())
                .product(new Product_Info().setProductId(dto.getProductId()))
                .productNum(dto.getProductNum())
                .member(new Member().setMemberId(dto.getMemberId()))
                .totalPrice(dto.getTotalPrice())
                .build();
        return entity;
    }

}
