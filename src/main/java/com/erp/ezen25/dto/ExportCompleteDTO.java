package com.erp.ezen25.dto;

import lombok.*;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExportCompleteDTO {

    private String name;
    private String orderCode;
    private Long productId;
    private String productName;
    private Long productNum;
    private Long exportNum;
    private String exportDate;

}
