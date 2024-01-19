package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Member;
import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.entity.Product_Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class StockRepositoryTests {
    @Autowired
    private StockRepository stockRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            // Product_Stock의 product 필드에 Product_Info 엔터티의 객체를 설정
            Product_Stock stock = Product_Stock.builder()
                    .product(Product_Info.builder().productId((long) i).build())
                    .productNum((long) (i * 10))
                    .member(Member.builder().memberId((long) i).build()) // Member 객체 생성 및 memberId 설정
                    .totalPrice((long) (i + i))
                    .build();
            System.out.println(stockRepository.save(stock));
        });
    }
}
