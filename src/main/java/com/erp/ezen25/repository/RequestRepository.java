package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long>, QuerydslPredicateExecutor<Request> {
    Optional<Request> findByBrandId(Long brandId);
}
