package com.erp.ezen25.service;

import com.erp.ezen25.dto.*;
import com.erp.ezen25.entity.Brand;
import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.repository.BrandRepository;
import com.erp.ezen25.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

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
    // 브랜드 가져오기
    public List<BrandNameListResponseDTO> getBrandList() {
        List<Brand> bList = brandRepository.findAll();
        return bList.stream()
                .map(BrandNameListResponseDTO::new)
                .toList();
    }

    // 상품등록하기
    public void createProduct(ProductGetRequestDTO getRequest, MultipartFile mf) throws IOException {
        getRequest.setImage(prodFileUpload(mf, uploadPath));

        Product_Info pInfo = getRequest.toEntity();
        productRepository.save(pInfo);
    }

    // 파일업로드
    public String prodFileUpload(MultipartFile mf, String uploadPath) throws IOException {
        long fileSize = mf.getSize();
        if (fileSize == 0) {
            return null;
        }
        LocalDate now = LocalDate.now();
        String uuid = UUID.randomUUID().toString();
        String orginMainName = mf.getOriginalFilename();
        String folderpath = makeFolder() + File.separator;
        String saveName = now+ uuid + orginMainName;
        File file = new File(uploadPath+folderpath+saveName);
        mf.transferTo(file);
        return saveName;
    }
    // 날짜 폴더 만들기
    private String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        // make folder ----------
        File uploadPathFolder = new File(uploadPath, folderPath);

        if (uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }
}
