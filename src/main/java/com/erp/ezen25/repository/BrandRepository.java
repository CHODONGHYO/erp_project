package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long>, QuerydslPredicateExecutor<Brand> {
    List<Brand> findAllByOrderByBrandNameAsc ();

    Brand findBrandByBrandId(Long brandId);
}
