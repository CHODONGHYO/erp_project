package com.erp.ezen25.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    private Long requestId;
    private String requestDate;
    private Long productId;
    private Long requestNum;
    private String requestDescription;
    private String requestOutDate;
    private String requestStatus;
    private Long brandId;
    private String requestCode;
}
