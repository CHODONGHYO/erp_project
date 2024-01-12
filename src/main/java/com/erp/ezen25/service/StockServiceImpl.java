package com.erp.ezen25.service;

import com.erp.ezen25.dto.*;
import com.erp.ezen25.entity.Order;
import com.erp.ezen25.entity.Product_Stock;
import com.erp.ezen25.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository repository;

    @Override
    public List<StockDTO> getList() {

        List<Product_Stock> result = repository.findAll();

        return result.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockDTO> getListWithProduct() {
        List<Object[]> result = repository.getImportDateWithImport();
        return result.stream()
                .map(this::objectArrayToStockDTO)
                .collect(Collectors.toList());
    }

    private StockDTO objectArrayToStockDTO(Object[] objects) {
        Product_Stock productStock = (Product_Stock) objects[0];
        Object importDate = objects[1];

        return StockDTO.builder()
                .pNumId(productStock.getPNumId())
                .productId(productStock.getProduct().getProductId())
                .productNum(productStock.getProductNum())
                .memberId(productStock.getMember().getMemberId())
                .totalPrice(productStock.getTotalPrice())
                .productName(productStock.getProduct().getProductName())
                .mCategory(productStock.getProduct().getMCategory())
                .sCategory(productStock.getProduct().getSCategory())
                .originalPrice(String.valueOf(productStock.getProduct().getOriginalPrice()))
                .sellPrice(String.valueOf(productStock.getProduct().getSellPrice()))
                .image(productStock.getProduct().getImage())
                .importDate(LocalDateTime.parse(importDate.toString()))
                .build();
    }

    private StockDTO entityToDtoWithProduct(Product_Stock entity) {
        return StockDTO.builder()
                .pNumId(entity.getPNumId())
                .productId(entity.getProduct().getProductId())
                .productNum(entity.getProductNum())
                .memberId(entity.getMember().getMemberId())
                .totalPrice(entity.getTotalPrice())
                .productName(entity.getProduct().getProductName())
                .mCategory(entity.getProduct().getMCategory())
                .sCategory(entity.getProduct().getSCategory())
                .originalPrice(String.valueOf(entity.getProduct().getOriginalPrice()))
                .sellPrice(String.valueOf(entity.getProduct().getSellPrice()))
                .image(entity.getProduct().getImage())
                .build();
    }
}
