package com.erp.ezen25.service;

import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.dto.RequestDTO;
import com.erp.ezen25.entity.Import;
import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.entity.QRequest;
import com.erp.ezen25.entity.Request;
import com.erp.ezen25.repository.ImportRepository;
import com.erp.ezen25.repository.RequestRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final ImportRepository importRepository;

    @Override
    public Long register(RequestDTO requestDTO) {
        Request request = dtoToEntity(requestDTO);

        requestRepository.save(request);

        Import i = Import.builder()
                .product(Product_Info.builder().build().setProductId(request.getProductId()))
                .importNum(request.getRequestNum())
                .importDate(request.getRequestOutDate())
                .requestCode(request.getRequestCode())
                .importStatus("미완")
                .build();

        importRepository.save(i);

        return request.getRequestId();
    }
    @Override
    public PageResultDTO<RequestDTO, Request> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("requestId").descending());

        BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);

        Page<Request> result = requestRepository.findAll(booleanBuilder, pageable);

        Function<Request, RequestDTO> fn = (this::entityToDTO);

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<RequestDTO, Request> getListByBrandId(PageRequestDTO pageRequestDTO, RequestDTO requestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("requestId").descending());

        BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);

        booleanBuilder.and(QRequest.request.brandId.eq(requestDTO.getBrandId()));

        Page<Request> result = requestRepository.findAll(booleanBuilder, pageable);

        Function<Request, RequestDTO> fn = (this::entityToDTO);

        return new PageResultDTO<>(result, fn);
    }



    @Override
    public RequestDTO read(Long requestId) {
        Optional<Request> oRequest = requestRepository.findById(requestId);

        return oRequest.map(this::entityToDTO).orElse(null);
    }
    @Override
    public void remove(Long requestId) {
        requestRepository.deleteById(requestId);
    }

    public void modify(RequestDTO requestDTO) {
        Optional<Request> oRequest = requestRepository.findById(requestDTO.getRequestId());

        if (oRequest.isPresent()) {
            Request request = oRequest.get();

            request.changeRequestDate(requestDTO.getRequestDate());
            request.changeProductId(requestDTO.getProductId());
            request.changeRequestNum(requestDTO.getRequestNum());
            request.changeRequestDescription(requestDTO.getRequestDescription());
            request.changeRequestOutDate(requestDTO.getRequestOutDate());
            request.changeRequestStatus(requestDTO.getRequestStatus());
            request.changeBrandId(requestDTO.getBrandId());
            request.changeRequestCode(requestDTO.getRequestCode());

            requestRepository.save(request);
        }
    }

    private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        BooleanBuilder builder = new BooleanBuilder();
        QRequest qRequest = QRequest.request;

        String keyword = pageRequestDTO.getKeyword();
        BooleanExpression expression = qRequest.requestId.gt(0L);
        builder.and(expression);

        if(type == null || type.trim().isEmpty()) {
            return builder;
        }

        BooleanBuilder sBuilder = new BooleanBuilder();

        if(type.contains("p")) {
            sBuilder.or(qRequest.productId.stringValue().contains(keyword));
        }

        if(type.contains("r")) {
            sBuilder.or(qRequest.requestCode.contains(keyword));
        }

        if(type.contains("s")) {
            sBuilder.or(qRequest.requestStatus.contains(keyword));
        }

        if(type.contains("d")) {
            sBuilder.or(qRequest.requestDate.contains(keyword));
        }

        builder.and(sBuilder);

        return builder;
    }
}
