package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @Column(nullable = false, unique = true)
    private LocalDateTime requestDate = now();

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long requestNum = 0L;

    @Column(length = 1000)
    private String requestDescription;

    @Column(nullable = false)
    private LocalDateTime requestOutDate = now();

    @Column(length = 1000, nullable = false)
    private String requestStatus = "미정";

    @Column(nullable = false)
    private Long brandId;

    @Column(length = 1000, nullable = false, unique = true)
    private String requestCode = "0";

}
