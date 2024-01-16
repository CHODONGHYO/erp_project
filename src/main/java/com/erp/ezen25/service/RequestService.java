package com.erp.ezen25.service;

import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.dto.RequestDTO;
import com.erp.ezen25.entity.Request;

public interface RequestService {
    Long register(RequestDTO requestDTO);
    PageResultDTO<RequestDTO, Request> getList(PageRequestDTO pageRequestDTO);

    RequestDTO read(Long requestId);

    void remove(Long requestId);

    void modify(RequestDTO requestDTO);

    default Request dtoToEntity(RequestDTO requestDTO) {
        Request request = Request.builder()
                .requestId(requestDTO.getRequestId())
                .requestDate(requestDTO.getRequestDate())
                .productId(requestDTO.getProductId())
                .requestNum(requestDTO.getRequestNum())
                .requestDescription(requestDTO.getRequestDescription())
                .requestStatus(requestDTO.getRequestStatus())
                .requestOutDate(requestDTO.getRequestOutDate())
                .brandId(requestDTO.getBrandId())
                .requestCode(requestDTO.getRequestCode())
                .build();
        return request;
    }

    default RequestDTO entityToDTO(Request request) {
        RequestDTO requestDTO = RequestDTO.builder()
                .requestId(request.getRequestId())
                .requestDate(request.getRequestDate())
                .productId(request.getProductId())
                .requestNum(request.getRequestNum())
                .requestDescription(request.getRequestDescription())
                .requestOutDate(request.getRequestOutDate())
                .requestStatus(request.getRequestStatus())
                .brandId(request.getBrandId())
                .requestCode(request.getRequestCode())
                .build();

        return requestDTO;
    }
}