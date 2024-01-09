package com.erp.ezen25.service;

import com.erp.ezen25.dto.OrderDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.dto.StockDTO;
import com.erp.ezen25.entity.Order;
import com.erp.ezen25.entity.Product_Stock;
import com.erp.ezen25.repository.OrderRepository;
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
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public PageResultDTO<OrderDTO, Order> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("orderId").descending());

        Page<Order> result = repository.findAll(pageable);

        Function<Order, OrderDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

}
