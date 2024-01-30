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



    Long register(OrderDTO orderDTO);
    PageResultDTO<OrderDTO, Order> getList(PageRequestDTO pageRequestDTO);
    OrderDTO read(Long orderId);

    void remove(Long orderId);

    List<OrderDTO> getList();
    void modify(OrderDTO orderDTO);

    List<WithdrawalDTO> getWithdrawalList(String orderCode);
    /*String getNameByOrderCode(String orderCode);*/
    List<OrderListDTO> getOrderListDTO();

    List<OrderDTO> getListByMemberId(Long memberId);
    List<OrderDTO> getmList(String orderCode);

    OrderDTO getOrderInfo(String orderCode);
    OrderDTO getOneOrderInfo(String orderCode);

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
}
