package com.erp.ezen25.service;

import com.erp.ezen25.dto.*;
import com.erp.ezen25.entity.Order;
import com.erp.ezen25.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public List<WithdrawalDTO> getWithdrawalList(String orderCode) {
        return repository.getWithdrawalDTO(orderCode);
    }

    @Override
    public List<OrderListDTO> getOrderListDTO() {
        return repository.getOrderListDTO();
    }

}
