package com.erp.ezen25.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@Table(name = "member")
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long memberId;

    @Column(length = 1000, nullable = false, unique = true)
    private String userId;

    @Column(length = 1000, nullable = false)
    private String password;

    @Column(length = 1000, nullable = false)
    private String email;

    @Column(length = 1000, nullable = false)
    private String name;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer percent;

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changePercent(Integer percent) {
        this.percent = percent;
    }

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<MemberRole> roleSet;

    public void addMemberRole(MemberRole memberRole) {
        roleSet.add(memberRole);
    }
}
