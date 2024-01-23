package com.erp.ezen25.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExportCheckDTO {
    private Long exportCheckId;
    private Long exportId;
    private String exportCheckStatus;
}
