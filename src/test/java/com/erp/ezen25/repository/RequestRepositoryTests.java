package com.erp.ezen25.repository;

import com.erp.ezen25.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

@SpringBootTest
public class RequestRepositoryTests {
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ImportRepository importRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImportCheckRepository importCheckRepository;


    @Test
    public void insertRequestDummies() {
        LocalDateTime time = LocalDateTime.now();
        String outDate = time.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        );

        IntStream.rangeClosed(1, 100).forEach(rDummies -> {
            Request request = Request.builder()
                    .requestDate(outDate)
                    .requestNum((long) rDummies)
                    .requestDescription("" + rDummies)
                    .requestOutDate((time.plusDays(5)).format(
                            DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    ))
                    .requestStatus("완료")
                    .requestCode(String.valueOf(rDummies))
                    .brandId((long) rDummies)
                    .productId((long) rDummies)
                    .build();

            requestRepository.save(request);
        });
    }

    @Test
    public void insertDummiesForImport() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Product_Info productInfo = Product_Info.builder()
                    .productName("p" + i)
                    .productDescription("This is a dummy product.")
                    .brand(Brand.builder().brandId(3L).build())
                    .mCategory("1")
                    // Set other fields as needed
                    .build();

            productRepository.save(productInfo);

            Import dummyImport = Import.builder()
                    .product(productInfo)
                    .importNum(10L)
                    .importDate(LocalDate.now().toString())
                    .requestCode("DUMMY_REQUEST_CODE")
                    .importStatus("미정")
                    // Set other fields as needed
                    .build();

            importRepository.save(dummyImport);
        });
    }

    @Test
    public void insertDummiesIC() {
        IntStream.rangeClosed(1, 100).forEach(ic -> {
            ImportCheck importCheck = ImportCheck.builder()
                    .importId(Import.builder().importId(222L).build())
                    .importCheckStatus("미완")
                    .build();

            importCheckRepository.save(importCheck);
        });
    }
}
