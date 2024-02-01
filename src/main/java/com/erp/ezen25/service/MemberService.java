package com.erp.ezen25.service;

import com.erp.ezen25.dto.MemberDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.entity.Member;
import com.erp.ezen25.entity.QMember;
import com.erp.ezen25.repository.MemberRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void remove(Long memberId) {
        memberRepository.deleteById(memberId);
    }

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
                .filter(memberDTO -> !isUserIdOrNameInBrand(memberDTO))
                .sorted(Comparator.comparing(MemberDTO::getMemberId).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    private boolean isUserIdOrNameInBrand(MemberDTO memberDTO) {
        String userId = memberDTO.getUserId();
        String name = memberDTO.getName();

        // Brand에 userId 또는 name이 존재하는지 확인하는 조건을 작성
        // 예시로 userId가 "Brand"에 포함되어 있으면 true 반환
        return userId != null && userId.contains("Brand") ||
                name != null && name.contains("Brand");
    }

    public int getNumberOfMembers() {
        List<Member> members = memberRepository.findAll();
        return members.size();
    }

    public PageResultDTO<MemberDTO, Member> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("memberId").descending());

        BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);

        Page<Member> result = memberRepository.findAll(booleanBuilder, pageable);

        Function<Member, MemberDTO> fn = (this::convertToDTO);

        return new PageResultDTO<>(result, fn);
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

    private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        BooleanBuilder builder = new BooleanBuilder();
        QMember qMember = QMember.member;

        String keyword = pageRequestDTO.getKeyword();
        BooleanExpression expression = qMember.memberId.gt(0L);
        builder.and(expression);

        if(type == null || type.trim().isEmpty()) {
            return builder;
        }

        BooleanBuilder sBuilder = new BooleanBuilder();

        if (type.contains("e")) {
            sBuilder.or(qMember.email.contains(keyword));
        }

        if (type.contains("n")) {
            sBuilder.or(qMember.name.contains(keyword));
        }


        builder.and(sBuilder);

        return builder;
    }
}
