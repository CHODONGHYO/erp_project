package com.erp.ezen25.service;

import com.erp.ezen25.dto.OrderDTO;
import com.erp.ezen25.entity.Order;


import java.util.List;

public interface OrderService {
    List<OrderDTO> getList();

    default Order dtoToEntity(OrderDTO dto) {
        Order entity = Order.builder()
                .orderId(dto.getOrderId())
                .memberId(dto.getMemberId())
                .orderDate(dto.getOrderDate())
                .productId(dto.getProductId())
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
                .memberId(entity.getMemberId())
                .orderDate(entity.getOrderDate())
                .productId(entity.getProductId())
                .orderNum(entity.getOrderNum())
                .orderDescription(entity.getOrderDescription())
                .orderOutDate(entity.getOrderOutDate())
                .orderCode(entity.getOrderCode())
                .orderStatus(entity.getOrderStatus())
                .build();
        return dto;
    }
}
