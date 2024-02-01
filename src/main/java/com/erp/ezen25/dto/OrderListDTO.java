package com.erp.ezen25.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderListDTO {

    private String name;
    private String orderCode;
    private String orderDate;
    private BigDecimal orderStatusSum;
    private Long orderStatusCnt;

}
