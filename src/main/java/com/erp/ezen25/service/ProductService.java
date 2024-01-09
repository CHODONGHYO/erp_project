package com.erp.ezen25.service;

import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product_Info> getproductList () {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "productId"));
    }
}
