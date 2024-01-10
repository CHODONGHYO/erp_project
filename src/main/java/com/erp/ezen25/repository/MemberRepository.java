package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
