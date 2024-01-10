package com.erp.ezen25.service;

import com.erp.ezen25.dto.BrandDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.entity.Brand;

public interface BrandService {
    Long register(BrandDTO brandDTO);
    PageResultDTO<BrandDTO, Brand> getList(PageRequestDTO pageRequestDTO);

    BrandDTO read(Long brandId);

    void remove(Long brandId);

    void modify(BrandDTO brandDTO);

    default Brand dtoToEntity(BrandDTO brandDTO) {
        Brand brand = Brand.builder()
                .brandId(brandDTO.getBrandId())
                .brandName(brandDTO.getBrandName())
                .brandPhone(brandDTO.getBrandPhone())
                .brandEmail(brandDTO.getBrandEmail())
                .brandDescription(brandDTO.getBrandDescription())
                .build();

        return brand;
    }

    default BrandDTO entityToDTO(Brand brand) {
        BrandDTO dto = BrandDTO.builder()
                .brandId(brand.getBrandId())
                .brandName(brand.getBrandName())
                .brandPhone(brand.getBrandPhone())
                .brandEmail(brand.getBrandEmail())
                .brandDescription(brand.getBrandDescription())
                .build();

        return dto;
    }
}
