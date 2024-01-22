package com.erp.ezen25.service;

import com.erp.ezen25.dto.ImportCheckDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.entity.ImportCheck;
import com.erp.ezen25.entity.QImportCheck;
import com.erp.ezen25.repository.ImportCheckRepository;
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
public class ImportCheckServiceImpl implements ImportCheckService{
    private final ImportCheckRepository importCheckRepository;

    @Override
    public Long register(ImportCheckDTO importCheckDTO) {
        ImportCheck ic = dtoToEntity(importCheckDTO);

        importCheckRepository.save(ic);

        return ic.getImportCheckId();
    }

    @Override
    public PageResultDTO<ImportCheckDTO, ImportCheck> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("importCheckId").descending());

        BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);

        Page<ImportCheck> result = importCheckRepository.findAll(booleanBuilder, pageable);

        Function<ImportCheck, ImportCheckDTO> fn = (this::entityToDTO);

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<ImportCheckDTO, ImportCheck> getReturnsList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("importCheckId").descending());
        QImportCheck qImportCheck = QImportCheck.importCheck;

        BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);
        booleanBuilder.and(qImportCheck.importCheckStatus.eq("반품"));

        Page<ImportCheck> result = importCheckRepository.findAll(booleanBuilder, pageable);

        Function<ImportCheck, ImportCheckDTO> fn = (this::entityToDTO);

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public ImportCheckDTO read(Long importCheckId) {
        Optional<ImportCheck> oImportCheck = importCheckRepository.findById(importCheckId);

        return oImportCheck.map(this::entityToDTO).orElse(null);
    }

    @Override
    public void remove(Long importCheckId) {
        importCheckRepository.deleteById(importCheckId);
    }

    @Override
    public void modify(ImportCheckDTO importCheckDTO) {
        Optional<ImportCheck> oImportCheck = importCheckRepository.findById(importCheckDTO.getImportId());

        if (oImportCheck.isPresent()) {
            ImportCheck ic = oImportCheck.get();
            ic.changeImportCheckStatus(importCheckDTO.getImportCheckStatus());
            importCheckRepository.save(ic);
        }
    }

    private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        BooleanBuilder builder = new BooleanBuilder();
        QImportCheck qImportCheck = QImportCheck.importCheck;

        String keyword = pageRequestDTO.getKeyword();
        BooleanExpression expression = qImportCheck.importCheckId.gt(0L);
        builder.and(expression);

        if(type == null || type.trim().isEmpty()) {
            return builder;
        }

        BooleanBuilder sBuilder = new BooleanBuilder();

        if (type.contains("n")) {
            sBuilder.or(qImportCheck.importCheckStatus.contains(keyword));
        }

        builder.and(sBuilder);

        return builder;
    }
}
