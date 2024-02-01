package com.erp.ezen25.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImportDTO {
    private Long importId;
    private Long productId;
    private Long importNum;
    private String importDate;
    private String requestCode;
    private String importStatus;
}
