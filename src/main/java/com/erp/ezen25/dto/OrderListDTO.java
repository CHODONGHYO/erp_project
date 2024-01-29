package com.erp.ezen25.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@Builder
public class OrderListDTO {

    private Long orderId;
    private Long memberId;
    private String name;
    private String orderDate;
    private Long productId;
    private Long orderNum;
    private String orderDescription;
    private String orderStatus;
    private String orderCode;

}
