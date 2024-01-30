package com.erp.ezen25.repository;

import com.erp.ezen25.dto.MemberDTO;
import com.erp.ezen25.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserId(String userId);
    Optional<Member> findByName(String name);

}
