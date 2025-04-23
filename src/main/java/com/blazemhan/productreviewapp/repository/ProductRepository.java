package com.blazemhan.productreviewapp.repository;

import com.blazemhan.productreviewapp.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameIgnoreCase(String productName, Pageable pageable);
}