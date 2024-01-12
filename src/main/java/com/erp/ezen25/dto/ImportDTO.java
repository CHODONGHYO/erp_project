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
public class ImportDTO {
    private Long importId;
    private Long productId;
    private Long productNum;
    private LocalDateTime importDate;
    private String requestCode;
    private String importStatus;
}
