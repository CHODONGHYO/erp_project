package com.erp.ezen25.service;

import com.erp.ezen25.dto.OrderDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.dto.StockDTO;
import com.erp.ezen25.entity.Order;
import com.erp.ezen25.entity.Product_Stock;

public interface OrderService {
    PageResultDTO<OrderDTO, Order> getList(PageRequestDTO requestDTO);

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
                .build();
        return dto;
    }
}
