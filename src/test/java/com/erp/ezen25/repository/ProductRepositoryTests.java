package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Product_Info;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;
@SpringBootTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1,30).forEach(i -> {
            Product_Info product = Product_Info.builder()
                    .productName("product"+i)
                    .productDescription("product"+i+" is ...")
                    .brandId((long) i)
                    .mCategory("mCategory"+(i%6))
                    .sCategory("sCategory"+i)
                    .originalPrice((long) (5000+i))
                    .sellPrice((long) (10000+i))
                    .image("img"+i+".jpg").build();
            System.out.println(productRepository.save(product));
        });
    }
}
