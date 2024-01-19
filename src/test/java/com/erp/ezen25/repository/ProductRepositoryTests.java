package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Brand;
import com.erp.ezen25.entity.Product_Info;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;
@SpringBootTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1,30).forEach(i -> {
            // Brand 엔터티 생성
            Brand brand = Brand.builder()
                    .brandName("Brand" + i)
                    .brandPhone("010-1234-5678")
                    .brandEmail("brand" + i + "@example.com")
                    .brandDescription("Brand Description " + i)
                    .build();
            brandRepository.save(brand);

            // Product_Info 엔터티 생성 및 Brand 객체 설정
            Product_Info product = Product_Info.builder()
                    .productName("product"+i)
                    .productDescription("product"+i+" is ...")
                    .brandId(brand)  // brandId에 Brand 객체 설정
                    .mCategory("mCategory"+(i%6))
                    .sCategory("sCategory"+i)
                    .originalPrice((long) (5000+i))
                    .sellPrice((long) (10000+i))
                    .image("img"+i+".jpg").build();
            System.out.println(productRepository.save(product));
        });
    }
}
