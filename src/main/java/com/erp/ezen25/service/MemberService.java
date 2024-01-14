package com.erp.ezen25.service;

import com.erp.ezen25.entity.Member;
import com.erp.ezen25.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public Member create(String userId, String password, String email, String name) {
        Member member = new Member();
        member.setUserId(userId);
        member.setEmail(email);
        member.setName(name);
        member.setPassword(passwordEncoder.encode(password));
        this.memberRepository.save(member);
        return member;
    }

}
