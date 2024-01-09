package com.erp.ezen25.repository;

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
        IntStream.rangeClosed(1,300).forEach(i -> {
            Product_Stock stock = Product_Stock.builder()
                    .productId((long) i)
                    .productNum((long) (i*10))
                    .memberId((long) i)
                    .totalPrice((long) (i+i)).build();
            System.out.println(stockRepository.save(stock));
        });
    }

}
