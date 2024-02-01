package com.erp.ezen25.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExportDTO {
    private Long exportId;
    private Long productId;
    private String image;
    private String productName;
    private String mCategory;
    private String sCategory;
    private Long orderNum; //발주 수량
    private Long productNum; //현재고
    private Long sellPrice; //단가
    private Long exportNum; //출고 수량
    private String orderStatus; //발주상태 0: 발주요청 1:불출 2: 출고처리완료
    private String exportDate;
    private String orderCode;
}
