package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Member;
import com.erp.ezen25.entity.Order;
import com.erp.ezen25.entity.Product_Info;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootTest
public class OrderRepositoryTests {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            Order order = Order.builder()
                    .member(new Member().setMemberId(1L))
                    .orderDate(LocalDateTime.now())
                    .product(new Product_Info().setProductId(1L))
                    .orderNum(100L)
                    .orderDescription("description" + i)
                    .orderOutDate(LocalDateTime.now())
                    .orderStatus("1")
                    .orderCode("10000" + i)
                    .build();
            System.out.println(orderRepository.save(order));
        });
    }
}
