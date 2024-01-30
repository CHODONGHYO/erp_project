package com.erp.ezen25.service;

import com.erp.ezen25.dto.MemberDTO;
import com.erp.ezen25.dto.OrderDTO;
import com.erp.ezen25.entity.Member;
import com.erp.ezen25.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private MemberDTO convertToDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(member.getMemberId());
        memberDTO.setUserId(member.getUserId());
        memberDTO.setPassword(member.getPassword());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setName(member.getName());
        // 필요에 따라 더 많은 매핑을 추가할 수 있습니다.
        return memberDTO;
    }
}
