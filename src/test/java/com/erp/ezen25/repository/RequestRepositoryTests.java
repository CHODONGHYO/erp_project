package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Request;
import jakarta.persistence.ManyToOne;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

import static java.time.LocalTime.now;

@SpringBootTest
public class RequestRepositoryTests {
    @Autowired
    private RequestRepository requestRepository;

    @Test
    public void insertRequestDummies() {
        LocalDateTime time = LocalDateTime.now();
        String outDate = time.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")
        );

        IntStream.rangeClosed(1, 100).forEach(rDummies -> {
            Request request = Request.builder()
                    .requestDate(outDate)
                    .requestNum((long) rDummies)
                    .requestDescription("" + rDummies)
                    .requestOutDate((time.plusDays(5)).format(
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")
                    ))
                    .requestStatus("완료")
                    .requestCode(String.valueOf(rDummies))
                    .build();

            requestRepository.save(request);
        });
    }
}
