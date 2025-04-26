package com.blazemhan.productreviewapp.controller;

import com.blazemhan.productreviewapp.model.Product;
import com.blazemhan.productreviewapp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Product> products;
        if (name != null && !name.isBlank()) {
            products = productService.getProductsByName(name, page, size);
        } else {
            products = productService.getAllProducts(page, size);
        }

        return ResponseEntity.ok(products);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product,
                                                    @PathVariable Long id) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestBody Map<String, String> searchKeyword) {

        List<Product> results = productService.searchProduct(searchKeyword);
        return ResponseEntity.ok(results);



    }


}
