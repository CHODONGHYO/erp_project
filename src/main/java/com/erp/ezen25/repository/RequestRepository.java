package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Brand;
import com.erp.ezen25.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface RequestRepository extends JpaRepository<Request, Long>, QuerydslPredicateExecutor<Request> {
}
