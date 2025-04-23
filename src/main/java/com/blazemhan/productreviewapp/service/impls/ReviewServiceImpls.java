package com.blazemhan.productreviewapp.service.impls;

import com.blazemhan.productreviewapp.model.Product;
import com.blazemhan.productreviewapp.model.Review;
import com.blazemhan.productreviewapp.repository.ProductRepository;
import com.blazemhan.productreviewapp.repository.ReviewRepository;
import com.blazemhan.productreviewapp.service.ReviewService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewServiceImpls implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public ReviewServiceImpls(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Review addReview(Long productId, Review review) {

        Product product = productRepository.findById(productId).
                orElseThrow(()-> new RuntimeException("Product not found"));

        review.setProduct(product);
        return reviewRepository.save(review);

    }

    @Override
    public List<Review> getReviewsForProduct(Long productId) {
        return reviewRepository.findByProductId(productId);
    }
}
