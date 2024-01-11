package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Product_Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Product_Stock, Long> {
}
