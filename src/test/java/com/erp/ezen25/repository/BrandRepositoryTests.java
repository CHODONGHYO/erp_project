package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class BrandRepositoryTests {
    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 200).forEach(dummy -> {
            Brand brand = Brand.builder()
                    .brandName("brand " + dummy)
                    .brandPhone("phone " + dummy)
                    .brandEmail("email " + dummy)
                    .brandDescription(dummy + "번 상품 계약")
                    .build();

            brandRepository.save(brand);
        });
    }
}
