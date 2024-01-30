package com.erp.ezen25.service;

import com.erp.ezen25.dto.OrderDTO;
import com.erp.ezen25.dto.OrderListDTO;
import com.erp.ezen25.dto.WithdrawalDTO;
import com.erp.ezen25.entity.Member;
import com.erp.ezen25.entity.Order;
import com.erp.ezen25.entity.Product_Info;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderService {
    List<WithdrawalDTO> getWithdrawalList(String orderCode);
    /*String getNameByOrderCode(String orderCode);*/
    List<OrderListDTO> getOrderListDTO();

    default Order dtoToEntity(OrderDTO dto) {
        Order entity = Order.builder()
                .orderId(dto.getOrderId())
                .member(new Member().setMemberId(dto.getMemberId()))
                .orderDate(dto.getOrderDate())
                .product(new Product_Info().setProductId(dto.getProductId()))
                .orderNum(dto.getOrderNum())
                .orderDescription(dto.getOrderDescription())
                .orderCode(dto.getOrderCode())
                .build();
        return entity;
    }
}
