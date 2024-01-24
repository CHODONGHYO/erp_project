package com.erp.ezen25.service;

import com.erp.ezen25.dto.ImportDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.entity.Import;
import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.entity.QImport;
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
public class ImportServiceImpl implements ImportService {

    private final ImportRepository importRepository;
    private final RequestRepository requestRepository;

    @Override
    public Long register(ImportDTO importDTO) {
        Import i = dtoToEntity(importDTO);

        importRepository.save(i);

        return i.getImportId();
    }
    @Override
    public PageResultDTO<ImportDTO, Import> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("importId").descending());

        BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);

        Page<Import> result = importRepository.findAll(booleanBuilder, pageable);

        Function<Import, ImportDTO> fn = (this::entityToDTO);

        return new PageResultDTO<>(result, fn);
    }
    @Override
    public ImportDTO read(Long importId) {
        Optional<Import> oImport = importRepository.findById(importId);

        return oImport.map(this::entityToDTO).orElse(null);
    }
    @Override
    public void remove(Long importId){
        Optional<Import> optionalImport = importRepository.findById(importId);

        if(optionalImport.isPresent()) {
            Import importEntity = optionalImport.get();

            // Delete the Import entity
            importRepository.deleteById(importId);

            // Get the requestCode from the Import entity
            String requestCode = importEntity.getRequestCode();

            // Find the related Request entity by requestCode
            Optional<Request> optionalRequest = requestRepository.findByRequestCode(requestCode);

            if(optionalRequest.isPresent()) {
                Request r = optionalRequest.get();
                r.changeRequestStatus("완료");
                requestRepository.save(r);
            }
        }
    }
    @Override
    public void modify(ImportDTO importDTO){
        Optional<Import> oImport = importRepository.findById(importDTO.getImportId());

        if (oImport.isPresent()) {
            Import i = oImport.get();
            i.changeProductId(Product_Info.builder().productId(importDTO.getProductId()).build());
            i.changeImportNum(importDTO.getImportNum());
            i.changeImportDate(importDTO.getImportDate());
            i.changeImportStatus(importDTO.getImportStatus());
            importRepository.save(i);
        }
    }

    private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        BooleanBuilder builder = new BooleanBuilder();
        QImport qImport = QImport.import$;

        String keyword = pageRequestDTO.getKeyword();
        BooleanExpression expression = qImport.importId.gt(0L);
        builder.and(expression);

        if(type == null || type.trim().isEmpty()) {
            return builder;
        }

        BooleanBuilder sBuilder = new BooleanBuilder();

        if (type.contains("s")) {
            sBuilder.or(qImport.importStatus.contains(keyword));
        }

        builder.and(sBuilder);

        return builder;
    }
}
