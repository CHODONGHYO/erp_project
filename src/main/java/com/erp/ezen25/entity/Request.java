package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime requestDate;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long requestNum;

    @Column(length = 1000)
    private String requestDescription;

    @Column(nullable = false)
    private LocalDateTime requestOutDate;

    @Column(length = 1000, nullable = false)
    private String requestStatus;

    @Column(nullable = false)
    private Long brandId;

    @Column(length = 1000, nullable = false, unique = true)
    private String requestCode;


}
