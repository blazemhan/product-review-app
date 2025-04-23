package com.blazemhan.productreviewapp.service;

import com.blazemhan.productreviewapp.model.Review;

import java.util.List;

public interface ReviewService {

    Review addReview(Long productId,Review review);
    List<Review> getReviewsForProduct(Long productId);

}
