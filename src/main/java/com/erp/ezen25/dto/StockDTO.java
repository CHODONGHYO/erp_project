package com.erp.ezen25.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockDTO {

    private Long pNumId;
    private Long productId;
    private Long productNum;
    private Long memberId;
    private Long totalPrice;

}
