package com.erp.ezen25.service;

import com.erp.ezen25.dto.*;
import com.erp.ezen25.entity.Export;
import com.erp.ezen25.entity.Member;
import com.erp.ezen25.entity.Order;
import com.erp.ezen25.entity.Product_Info;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface OrderService {

    Long register(OrderDTO orderDTO);
    PageResultDTO<OrderDTO, Order> getList(PageRequestDTO pageRequestDTO);
    OrderDTO read(Long orderId);


    void remove(Long orderId);

    List<OrderDTO> getList();
    void modify(OrderDTO orderDTO);
    List<WithdrawalDTO> getWithdrawalList(String orderCode);
    String getNameByOrderCode(String orderCode);

    List<OrderDTO> getListByMemberId(Long memberId);

    List<String> getMCategoryList();
    // 서브 카테고리 가져오기
    List<String> getSCategoryList(String upperCategory);

    List<String> getProductList(String subcategory);


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

    default OrderDTO entityToDto(Order entity) {
        OrderDTO dto = OrderDTO.builder()
                .orderId(entity.getOrderId())
                .memberId(entity.getMember().getMemberId())
                .name(entity.getMember().getName())
                .orderDate(entity.getOrderDate())
                .productId(entity.getProduct().getProductId())
                .orderNum(entity.getOrderNum())
                .orderDescription(entity.getOrderDescription())
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
