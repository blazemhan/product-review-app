package com.blazemhan.productreviewapp.service;

import com.blazemhan.productreviewapp.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);
    Product getProduct(Long id);
    Page<Product> getProductsByName(String productName, int page, int size);
    Page<Product> getAllProducts(int page, int size);
    void delete(Long id);
    Product updateProduct(Long productId, Product product);

}
