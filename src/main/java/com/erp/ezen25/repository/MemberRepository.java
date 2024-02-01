package com.erp.ezen25.repository;

import com.erp.ezen25.dto.MemberDTO;
import com.erp.ezen25.entity.Brand;
import com.erp.ezen25.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
    Optional<Member> findByUserId(String userId);
    Optional<Member> findByName(String name);

}
