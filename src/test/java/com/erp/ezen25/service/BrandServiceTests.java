package com.erp.ezen25.service;

import com.erp.ezen25.dto.BrandDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BrandServiceTests {
    @Autowired
    private BrandService brandService;

    @Test
    public void testRegister() {
        BrandDTO brandDTO = BrandDTO.builder()
                .brandName("동서식품")
                .brandPhone("010-1234-1234")
                .brandEmail("test@test.com")
                .brandDescription("맥심커피 5년계약")
                .build();

        brandService.register(brandDTO);
    }

}
