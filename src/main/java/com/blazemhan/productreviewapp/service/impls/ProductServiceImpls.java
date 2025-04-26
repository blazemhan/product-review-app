package com.blazemhan.productreviewapp.service.impls;

import com.blazemhan.productreviewapp.model.Product;
import com.blazemhan.productreviewapp.repository.ProductRepository;
import com.blazemhan.productreviewapp.service.ProductService;
import com.blazemhan.productreviewapp.utils.ProductSpecs;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpls implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpls(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Cacheable(value = "products", key = "#id")
    @Override
    public Product getProduct(Long id) {
        System.out.println("Getting product from DB");
        return productRepository.findById(id).orElseThrow(()->
                new RuntimeException("Product Not Found"));
    }

    @Override
    public Page<Product> getProductsByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return productRepository.findByNameIgnoreCase(name, pageable);
    }

    @Override
    public Page<Product> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return productRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public Product updateProduct(Long productid, Product product) {
        Product product1 = productRepository.findById(productid).orElseThrow(()->new
                RuntimeException("Product Not Found"));
        
        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        return productRepository.save(product1);
    }
    @Override
    public List<Product> searchProduct(Map<String, String> searchCriteria){
        Specification<Product> spec = Specification.where(null);

        if(StringUtils.hasLength(searchCriteria.get("name"))){
            spec = spec.and(ProductSpecs.containsName(searchCriteria.get("name")));
        }

        if(StringUtils.hasLength(searchCriteria.get("description"))){
            spec = spec.and(ProductSpecs.containsDescription(searchCriteria.get("description")));
        }

        return productRepository.findAll(spec);

    }

    }

