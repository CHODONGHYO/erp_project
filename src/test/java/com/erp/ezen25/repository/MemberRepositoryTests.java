package com.erp.ezen25.repository;


import com.erp.ezen25.entity.Member;
import com.erp.ezen25.entity.MemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.IntStream;
@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummy() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            Member member = Member.builder()
                    .userId("user"+i)
                    .password(passwordEncoder.encode("1234"))
                    .email("user"+i+"@test.com")
                    .name("사용자"+i)
                    .percent((int) (Math.random() * 100)+1)
                    .roleSet(new HashSet<MemberRole>())
                    .build();
            if (i <= 80) {
                member.addMemberRole(MemberRole.BRANCH);
            }

            if (i > 80 && i <= 90) {
                member.addMemberRole(MemberRole.ADMIN);
            }

            if (i > 90) {
                member.addMemberRole(MemberRole.PARTNER);
            }

            memberRepository.save(member);
        });
    }

    @Test
    public void testRead() {
        Optional<Member> result = memberRepository.findByUserId("user50");

        Member member = result.get();

        System.out.println(member);
    }

}
