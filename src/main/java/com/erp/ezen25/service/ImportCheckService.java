package com.erp.ezen25.service;

import com.erp.ezen25.dto.ImportCheckDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.entity.Import;
import com.erp.ezen25.entity.ImportCheck;

public interface ImportCheckService {
    Long register(ImportCheckDTO importCheckDTO);
    PageResultDTO<ImportCheckDTO, ImportCheck> getList(PageRequestDTO pageRequestDTO);

    /*PageResultDTO<ImportCheckDTO, ImportCheck> getListReturns(PageRequestDTO pageRequestDTO);*/

    ImportCheckDTO read(Long importCheckId);

    void remove(Long importCheckId);

    void modify(ImportCheckDTO importCheckDTO);

    PageResultDTO<ImportCheckDTO, ImportCheck> getReturnsList(PageRequestDTO pageRequestDTO);

    default ImportCheck dtoToEntity(ImportCheckDTO importCheckDTO) {
        ImportCheck entity = ImportCheck.builder()
                .importCheckId(importCheckDTO.getImportCheckId())
                .importId(new Import().setImportId(importCheckDTO.getImportId()))
                .importCheckStatus(importCheckDTO.getImportCheckStatus())
                .build();

        return entity;
    }

    default ImportCheckDTO entityToDTO(ImportCheck importCheck) {
        ImportCheckDTO dto = ImportCheckDTO.builder()
                .importCheckId(importCheck.getImportCheckId())
                .importId(importCheck.getImportCheckId())
                .importCheckStatus(importCheck.getImportCheckStatus())
                .build();

        return dto;
    }
}
