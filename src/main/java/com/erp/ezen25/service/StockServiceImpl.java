package com.erp.ezen25.service;

import com.erp.ezen25.dto.*;
import com.erp.ezen25.entity.Export;
import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.entity.Product_Stock;
import com.erp.ezen25.repository.ProductRepository;
import com.erp.ezen25.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    @Transactional(readOnly = true)
    public List<StockDTO> getListWithProduct() {
        List<Object[]> result = stockRepository.getImportDateWithImport();
        return result.stream()
                .map(this::objectArrayToStockDTOWithProduct)
                .collect(Collectors.toList());
    }

    private StockDTO objectArrayToStockDTOWithProduct(Object[] objects) {
        Product_Stock Stock = (Product_Stock) objects[0];
        Hibernate.initialize(Stock.getProduct());
        Product_Info product = Stock.getProduct();

        StockDTO dto = StockDTO.builder()
                .pNumId(Stock.getPNumId())
                .productId(product.getProductId())
                .productNum(Stock.getProductNum())
                .memberId(Stock.getMember().getMemberId())
                .totalPrice(Stock.getTotalPrice())
                .productName(product.getProductName())
                .mCategory(product.getMCategory())
                .sCategory(product.getSCategory())
                .originalPrice(String.valueOf(product.getOriginalPrice()))
                .sellPrice(String.valueOf(product.getSellPrice()))
                .image(product.getImage())
                .importDate(LocalDateTime.parse(objects[1].toString()))
                .build();

        dto.setProduct(product);

        return dto;
    }

    @Override
    @Transactional
    public void updateOrderStatus(String orderCode, List<Long> productIds) {
        stockRepository.updateOrderStatus(orderCode, productIds);
    }
}