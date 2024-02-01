package com.erp.ezen25.service;

import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.dto.productDTOs.*;
import com.erp.ezen25.entity.Brand;
import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.entity.Product_Stock;
import com.erp.ezen25.entity.QProduct_Info;
import com.erp.ezen25.repository.BrandRepository;
import com.erp.ezen25.repository.MemberRepository;
import com.erp.ezen25.repository.ProductRepository;
import com.erp.ezen25.repository.StockRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class ProductService {
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final MemberRepository memberRepository;

    // 리스트 받기
    public List<ProductListResponseDTO> getproductList () {
        List<Product_Info> pInfoList = productRepository.findAll(Sort.by(Sort.Direction.DESC, "productId"));
        return pInfoList.stream()
                .map(ProductListResponseDTO::new)
                .toList();
    }
    
    // 주 카테고리 값 가져오기
    public List<ProductMCateListResponseDTO> getMCategoryList() {
        return productRepository.productInfoGroupByMCategory().stream()
                .map(ProductMCateListResponseDTO::new)
                .toList();
    };
    // 서브 카테고리 가져오기
    public List<ProductSCateListResponseDTO> getSCategoryList() {
        return productRepository.productInfoGroupBySCategory().stream()
                .map(ProductSCateListResponseDTO::new)
                .toList();
    };
    // 브랜드 가져오기
    public List<ProductBnameListResponseDTO> getBrandList() {
        List<Brand> bList = brandRepository.findAll();
        return bList.stream()
                .map(ProductBnameListResponseDTO::new)
                .toList();
    }

    // 상품등록하기
    public void createProduct(ProductGetRequestDTO getRequest, MultipartFile mf) throws IOException {
        getRequest.setImage(prodFileUpload(mf, uploadPath));

        Product_Info pInfo = getRequest.toEntity();
        productRepository.save(pInfo);

        ProductStockAddReqeustDTO pSt = new ProductStockAddReqeustDTO();

        pSt.setProduct(pInfo);
        pSt.setMember(memberRepository.getReferenceById(1L));
        stockRepository.save(pSt.toEntity());
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

    // 품목 삭제
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
    // 품목 상세조회
    public ProductDetailResponseDTO productdetail(Long productId) {
        Optional<Product_Info> OppInfo = productRepository.findById(productId);
        Product_Info pInfo = OppInfo.orElse(null);

        return new ProductDetailResponseDTO(pInfo);
    }

    // 품목 수정하기
    public void updateProduct(ProductUpdateRequestDTO updateRequest, MultipartFile mf) throws IOException {
        String imgURL = prodFileUpload(mf, uploadPath);

        if (imgURL != null) {
            updateRequest.setImage(imgURL);
        }

        Product_Info pInfo = updateRequest.toEntity();
        productRepository.save(pInfo);
    }

    public String getProductName(Long productId) {
        // Implement logic to retrieve the product name from Product_Info entity
        // You can use your repository or any other method to fetch data
        Product_Info productInfo = productRepository.findByProductId(productId);

        // Check if the productInfo is not null before accessing the productName
        return (productInfo != null) ? productInfo.getProductName() : "Unknown Product";
    }

    public PageResultDTO<ProductDTO, Product_Info> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("productId").descending());

        BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);

        Page<Product_Info> result = productRepository.findAll(booleanBuilder, pageable);

        Function<Product_Info, ProductDTO> fn = (this::entityToDTO);

        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        BooleanBuilder builder = new BooleanBuilder();
        QProduct_Info qProductInfo = QProduct_Info.product_Info;

        String keyword = pageRequestDTO.getKeyword();
        BooleanExpression expression = qProductInfo.productId.gt(0L);
        builder.and(expression);

        if(type == null || type.trim().isEmpty()) {
            return builder;
        }

        BooleanBuilder sBuilder = new BooleanBuilder();

        if (type.contains("pn")) {
            sBuilder.or(qProductInfo.productName.contains(keyword));
        }

        if (type.contains("pc")) {
            sBuilder.or(qProductInfo.productId.stringValue().contains(keyword));
        }

        if (type.contains("mc")) {
            sBuilder.or(qProductInfo.mCategory.contains(keyword));
        }

        if (type.contains("sc")) {
            sBuilder.or(qProductInfo.sCategory.contains(keyword));
        }

        if (type.contains("bn")) {
            sBuilder.or(qProductInfo.brand.brandName.contains(keyword));
        }

        if (type.contains("bc")) {
            sBuilder.or(qProductInfo.brand.brandId.stringValue().contains(keyword));
        }

        builder.and(sBuilder);

        return builder;
    }

    ProductDTO entityToDTO(Product_Info productInfo) {
        ProductDTO dto = ProductDTO.builder()
                .productId(productInfo.getProductId())
                .productName(productInfo.getProductName())
                .image(productInfo.getImage())
                .mCategory(productInfo.getMCategory())
                .sCategory(productInfo.getSCategory())
                .brandId(productInfo.getBrand().getBrandId())
                .productDescription(productInfo.getProductDescription())
                .originalPrice(productInfo.getOriginalPrice())
                .sellPrice(productInfo.getSellPrice())
                .build();

        return dto;
    }
}
