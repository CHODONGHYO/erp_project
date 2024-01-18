package com.erp.ezen25.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@Builder
public class OrderListDTO {

    private Long orderId;
    private String name;
    private String orderCode;
    private LocalDateTime orderDate;
    private String orderStatus;

}
