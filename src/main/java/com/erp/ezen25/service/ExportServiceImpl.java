package com.erp.ezen25.service;

import com.erp.ezen25.dto.ExportDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.entity.Export;
import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.entity.QExport;
import com.erp.ezen25.repository.ExportRepository;
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
public class ExportServiceImpl implements ExportService {
    private final ExportRepository exportRepository;


    @Override
    public Long register(ExportDTO exportDTO) {
        Export e = dtoToEntity(exportDTO);
        exportRepository.save(e);
        return e.getExportId();
    }

    @Override
    public PageResultDTO<ExportDTO, Export> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("exportId").descending());

        BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);

        Page<Export> result = exportRepository.findAll(booleanBuilder, pageable);

        Function<Export, ExportDTO> fn = (this::entityToDTO);

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public ExportDTO read(Long exportId) {
        Optional<Export> oExport = exportRepository.findById(exportId);

        return oExport.map(this::entityToDTO).orElse(null);
    }

    @Override
    public void remove(Long exportId) {
        exportRepository.deleteById(exportId);
    }

    @Override
    public void modify(ExportDTO exportDTO) {
        Optional<Export> oExport = exportRepository.findById(exportDTO.getExportId());

        if (oExport.isPresent()) {
            Export e = oExport.get();
            e.changeProductId(Product_Info.builder().productId(exportDTO.getProductId()).build());
            e.changeExportNum(exportDTO.getExportNum());
            e.changeExportDate(exportDTO.getExportDate());
            e.changeExportStatus(exportDTO.getExportStatus());
            exportRepository.save(e);
        }
    }

    private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        BooleanBuilder builder = new BooleanBuilder();
        QExport qExport = QExport.export;

        String keyword = pageRequestDTO.getKeyword();
        BooleanExpression expression = qExport.exportId.gt(0L);
        builder.and(expression);

        if(type == null || type.trim().isEmpty()) {
            return builder;
        }

        BooleanBuilder sBuilder = new BooleanBuilder();

        if (type.contains("s")) {
            sBuilder.or(qExport.exportStatus.contains(keyword));
        }

        builder.and(sBuilder);

        return builder;
    }
}
