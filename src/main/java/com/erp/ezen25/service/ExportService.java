package com.erp.ezen25.service;

import com.erp.ezen25.dto.ExportDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.entity.Export;
import com.erp.ezen25.entity.Product_Info;

public interface ExportService {
    Long register(ExportDTO exportDTO);
    PageResultDTO<ExportDTO, Export> getList(PageRequestDTO pageRequestDTO);

    ExportDTO read(Long exportId);

    void remove(Long exportId);

    void modify(ExportDTO exportDTO);

    default Export dtoToEntity(ExportDTO exportDTO) {
        Export entity = Export.builder()
                .exportId(exportDTO.getExportId())
                .product(new Product_Info().setProductId(exportDTO.getProductId()))
                .exportNum(exportDTO.getExportNum())
                .exportDate(exportDTO.getExportDate())
                .orderCode(exportDTO.getOrderCode())
                .exportStatus(exportDTO.getExportStatus())
                .build();

        return entity;
    }

    default ExportDTO entityToDTO(Export export) {
        ExportDTO dto = ExportDTO.builder()
                .exportId(export.getExportId())
                .productId(export.getProduct().getProductId())
                .exportNum(export.getExportNum())
                .exportDate(export.getExportDate())
                .orderCode(export.getOrderCode())
                .exportStatus(export.getExportStatus())
                .build();

        return dto;
    }
}
