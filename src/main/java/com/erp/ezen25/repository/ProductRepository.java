package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Product_Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product_Info, Long> {
}
