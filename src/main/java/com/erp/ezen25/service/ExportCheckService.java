package com.erp.ezen25.service;

import com.erp.ezen25.dto.ExportCheckDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.entity.Export;
import com.erp.ezen25.entity.ExportCheck;

public interface ExportCheckService {
    Long register(ExportCheckDTO exportCheckDTO);
    PageResultDTO<ExportCheckDTO, ExportCheck> getList(PageRequestDTO pageRequestDTO);
    ExportCheckDTO read(Long exportCheckId);
    void remove(Long exportCheckId);
    void modify(ExportCheckDTO exportCheckDTO);
    PageResultDTO<ExportCheckDTO, ExportCheck> getReturnsList(PageRequestDTO pageRequestDTO);

    default ExportCheck dtoToEntity(ExportCheckDTO exportCheckDTO) {
        ExportCheck entity = ExportCheck.builder()
                .exportCheckId(exportCheckDTO.getExportCheckId())
                .exportId(new Export().setExportId(exportCheckDTO.getExportId()))
                .exportCheckStatus(exportCheckDTO.getExportCheckStatus())
                .build();

        return entity;
    }

    default ExportCheckDTO entityToDTO(ExportCheck exportCheck) {
        ExportCheckDTO dto = ExportCheckDTO.builder()
                .exportCheckId(exportCheck.getExportCheckId())
                .exportId(exportCheck.getExportId().getExportId())
                .exportCheckStatus(exportCheck.getExportCheckStatus())
                .build();

        return dto;
    }
}
