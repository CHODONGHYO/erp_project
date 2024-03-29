package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
@DynamicInsert
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountId")
    private Long accountId;

    @OneToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Long memberId;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long money;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long netMoney;

}
