package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long memberId;

    @Column(length = 1000, nullable = false, unique = true)
    private String userId;

    @Column(length = 1000, nullable = false)
    private String password;

    @Column(length = 1000, nullable = false)
    private String authority;

    @Column(length = 1000, nullable = false)
    private String email;

    @Column(length = 1000, nullable = false)
    private String name;

    @Column(nullable = false)
    private Long percent = 0L;


}
