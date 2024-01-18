package com.erp.ezen25.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {

    private Long orderId;
    private Long memberId;
    private String name;
    private LocalDateTime orderDate;
    private Long productId;
    private Long orderNum;
    private String orderDescription;
    private LocalDateTime orderOutDate;
    private String orderStatus;
    private String orderCode;

}
