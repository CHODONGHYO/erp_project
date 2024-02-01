package com.erp.ezen25.service;

import com.erp.ezen25.dto.ImportDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.entity.Import;
import com.erp.ezen25.entity.Product_Info;

public interface ImportService {
    Long register(ImportDTO importDTO);
    PageResultDTO<ImportDTO, Import> getList(PageRequestDTO pageRequestDTO);

    ImportDTO read(Long importId);

    void remove(Long importId);

    void modify(ImportDTO importDTO);
    String showProductInfo(Long productId);

    String findRequestCodeByImportId(Long importId);
    Long findProductIdByImportId(Long importId);
    Long findImportNumByImportId(Long importId);
    String findImportDateByImportId(Long importId);

    default Import dtoToEntity(ImportDTO importDTO) {
        Import entity = Import.builder()
                .importId(importDTO.getImportId())
                .product(new Product_Info().setProductId(importDTO.getProductId()))
                .importNum(importDTO.getImportNum())
                .importDate(importDTO.getImportDate())
                .requestCode(importDTO.getRequestCode())
                .importStatus(importDTO.getImportStatus())
                .build();

        return entity;
    }

    default ImportDTO entityToDTO(Import eImport) {
        ImportDTO dto = ImportDTO.builder()
                .importId(eImport.getImportId())
                .productId(eImport.getProduct().getProductId())
                .importNum(eImport.getImportNum())
                .importDate(eImport.getImportDate())
                .requestCode(eImport.getRequestCode())
                .importStatus(eImport.getImportStatus())
                .build();

        return dto;
    }
}
