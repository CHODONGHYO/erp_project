package com.erp.ezen25.repository;

import com.erp.ezen25.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

@SpringBootTest
public class OrderRepositoryTests {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ExportRepository exportRepository;

    LocalDateTime time = LocalDateTime.now();
    String outDate = time.format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
    );

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Order order = Order.builder()
                    .member(new Member().setMemberId(1L))
                    .orderDate(String.valueOf(LocalDateTime.now()))
                    .product(new Product_Info().setProductId(1L))
                    .orderNum(100L)
                    .orderDescription("description" + i)
                    .orderStatus("1")
                    .orderCode("10000" + i)
                    .build();
            System.out.println(orderRepository.save(order));
        });
    }

    @Test
    public void insertDummiesExport() {
        IntStream.rangeClosed(1, 100).forEach(e -> {
            Export dummyExport = Export.builder()
                    .productId(Product_Info.builder().productId(410L).build())
                    .exportNum((long) e)
                    .exportDate(outDate)
                    .orderCode("" + e + e)
                    .build();

            exportRepository.save(dummyExport);
        });
    }
}
