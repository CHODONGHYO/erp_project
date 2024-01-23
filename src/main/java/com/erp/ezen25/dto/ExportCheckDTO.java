package com.erp.ezen25.dto;

import com.erp.ezen25.entity.Export;
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
    private Export exportId;
    private String exportCheckStatus;
}
