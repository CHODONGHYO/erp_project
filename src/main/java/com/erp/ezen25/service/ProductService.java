package com.erp.ezen25.service;

import com.erp.ezen25.dto.*;
import com.erp.ezen25.entity.Brand;
import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.repository.BrandRepository;
import com.erp.ezen25.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    // 리스트 받기
    public List<ProductListResponseDTO> getproductList () {
        List<Product_Info> pInfoList = productRepository.findAll(Sort.by(Sort.Direction.DESC, "productId"));
        return pInfoList.stream()
                .map(ProductListResponseDTO::new)
                .toList();
    }
    
    // 주 카테고리 값 가져오기
    public List<MCategoryListResponseDTO> getMCategoryList() {
        return productRepository.productInfoGroupByMCategory().stream()
                .map(MCategoryListResponseDTO::new)
                .toList();
    };
    // 서브 카테고리 가져오기
    public List<SCategoryListResponseDTO> getSCategoryList() {
        return productRepository.productInfoGroupBySCategory().stream()
                .map(SCategoryListResponseDTO::new)
                .toList();
    };
    // 브래드 가져오기
    public List<BrandNameListResponseDTO> getBrandList() {
        List<Brand> bList = brandRepository.findAll();
        return bList.stream()
                .map(BrandNameListResponseDTO::new)
                .toList();
    }

}
