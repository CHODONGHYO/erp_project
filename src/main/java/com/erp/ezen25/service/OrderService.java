package com.erp.ezen25.service;

import com.erp.ezen25.dto.OrderDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.entity.Member;
import com.erp.ezen25.entity.Order;
import com.erp.ezen25.entity.Product_Info;


import java.util.List;

public interface OrderService {
    List<OrderDTO> getList();
    PageResultDTO<OrderDTO, Order> getWithdrawalList(PageRequestDTO requestDTO);

    default Order dtoToEntity(OrderDTO dto) {
        Order entity = Order.builder()
                .orderId(dto.getOrderId())
                .member(new Member().setMemberId(dto.getMemberId()))
                .orderDate(dto.getOrderDate())
                .product(new Product_Info().setProductId(dto.getProductId()))
                .orderNum(dto.getOrderNum())
                .orderDescription(dto.getOrderDescription())
                .orderOutDate(dto.getOrderOutDate())
                .orderCode(dto.getOrderCode())
                .build();
        return entity;
    }

    default OrderDTO entityToDto(Order entity) {
        OrderDTO dto = OrderDTO.builder()
                .orderId(entity.getOrderId())
                .memberId(entity.getMember().getMemberId())
                .orderDate(entity.getOrderDate())
                .productId(entity.getProduct().getProductId())
                .orderNum(entity.getOrderNum())
                .orderDescription(entity.getOrderDescription())
                .orderOutDate(entity.getOrderOutDate())
                .orderCode(entity.getOrderCode())
                .orderStatus(entity.getOrderStatus())
                .build();
        return dto;
    }
}
