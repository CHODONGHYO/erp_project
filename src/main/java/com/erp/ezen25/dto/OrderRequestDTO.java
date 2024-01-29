package com.erp.ezen25.dto;

import com.erp.ezen25.dto.OrderItemDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequestDTO {
    private Long orderId;
    private Long memberId;
    private String name;
    private List<OrderItemDTO> items;  // List of items, each containing productId and orderNum
    private String orderDescription;
    private String orderStatus;
    private String orderCode;
}
