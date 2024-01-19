package com.erp.ezen25.service;

import com.erp.ezen25.dto.*;
import com.erp.ezen25.entity.Order;
import com.erp.ezen25.entity.Product_Stock;
import com.erp.ezen25.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public List<OrderDTO> getList() {

        List<Order> result = repository.findAll();

        return result.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WithdrawalDTO> getWithdrawalList(String orderCode) {

        List<Order> result = repository.findByOrderCode(orderCode); /*발주요청리스트에서 보낼 orderCode*/

        return result.stream()
                .map(this::entityToDtoForWithdrawal)
                .collect(Collectors.toList());
    }

    @Override
    public String getNameByOrderCode(String orderCode) {
        List<Order> result = repository.findByOrderCode(orderCode);
        System.out.println("result:" + result);
        if (!result.isEmpty()) {
            return result.get(0).getMember().getName();
        } else {
            // Handle the case where the list is empty
            log.error("No order found for orderCode: {}", orderCode);
            return "";
        }
    }

}
