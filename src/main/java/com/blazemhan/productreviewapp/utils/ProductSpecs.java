package com.blazemhan.productreviewapp.utils;

import com.blazemhan.productreviewapp.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecs {

    public static Specification<Product> containsName(String providedName){

        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+providedName.toLowerCase()+"%"));

    }

    public static Specification<Product> containsDescription(String providedDescription){

        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%"+providedDescription.toLowerCase()+"%"));

    }
}
