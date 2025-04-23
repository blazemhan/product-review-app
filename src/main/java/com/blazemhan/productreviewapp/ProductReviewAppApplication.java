package com.blazemhan.productreviewapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProductReviewAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductReviewAppApplication.class, args);
    }

}
