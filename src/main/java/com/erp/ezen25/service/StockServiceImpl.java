package com.erp.ezen25.service;

import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.dto.ProductDTO;
import com.erp.ezen25.dto.StockDTO;
import com.erp.ezen25.entity.Product_Stock;
import com.erp.ezen25.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository repository;

    @Override
    public PageResultDTO<StockDTO, Product_Stock> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("pNumId").descending());

        Page<Product_Stock> result = repository.findAll(pageable);

        Function<Product_Stock, StockDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }
}
