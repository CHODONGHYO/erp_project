package com.erp.ezen25.service;

import com.erp.ezen25.dto.*;
import com.erp.ezen25.entity.Member;
import com.erp.ezen25.entity.Order;
import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.queryMapping.SCategoryListMapping;
import com.erp.ezen25.repository.OrderRepository;
import com.erp.ezen25.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public interface OrderService {


    List<Object[]> joinOrderAndProduct();
    List<OrderDTO> getList();
    List<WithdrawalDTO> getWithdrawalList(String orderCode);
    String getNameByOrderCode(String orderCode);
    OrderDTO findByOrderId(Long orderId);

    List<OrderDTO> getListByMemberId(Long memberId);
    void deleteById(Long orderId);

    List<String> getMCategoryList();
    List<String> getSCategoryList(String uppercateno);
    // 서브 카테고리 가져오기
    List<String> getProductList(String subcategory);




    /*void modify(OrderDTO dto);*/


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
                .name(entity.getMember().getName())
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

    default  WithdrawalDTO entityToDtoForWithdrawal(Order entity) {
        WithdrawalDTO dto = WithdrawalDTO.builder()
                .memberId(entity.getMember().getMemberId())
                .name(entity.getMember().getName())
                .productId(entity.getProduct().getProductId())
                .productName(entity.getProduct().getProductName())
                .orderCode(entity.getOrderCode())
                .orderId(entity.getOrderId())
                .image(entity.getProduct().getImage())
                .mCategory(entity.getProduct().getMCategory())
                .sCategory(entity.getProduct().getSCategory())
                .orderNum(entity.getOrderNum())
                .build();
        return dto;
    }
}