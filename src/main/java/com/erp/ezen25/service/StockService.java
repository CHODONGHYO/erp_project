package com.erp.ezen25.service;

import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.dto.StockDTO;
import com.erp.ezen25.entity.Product_Stock;

public interface StockService {

    PageResultDTO<StockDTO, Product_Stock> getList(PageRequestDTO requestDTO);

    default Product_Stock dtoToEntity(StockDTO dto) {
        Product_Stock entity = Product_Stock.builder()
                .pNumId(dto.getPNumId())
                .productId(dto.getProductId())
                .productNum(dto.getProductNum())
                .memberId(dto.getMemberId())
                .totalPrice(dto.getTotalPrice())
                .build();
        return entity;
    }

    default StockDTO entityToDto(Product_Stock entity) {
        StockDTO dto = StockDTO.builder()
                .pNumId(entity.getPNumId())
                .productId(entity.getProductId())
                .productNum(entity.getProductNum())
                .memberId(entity.getMemberId())
                .totalPrice(entity.getTotalPrice())
                .build();
        return dto;
    }
}
