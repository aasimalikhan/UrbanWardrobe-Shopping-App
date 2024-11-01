package com.urbanwardrobe.app.service;

import com.urbanwardrobe.app.exception.ProductException;
import com.urbanwardrobe.app.model.Review;
import com.urbanwardrobe.app.model.User;
import com.urbanwardrobe.app.request.ReviewRequest;

import java.util.List;

public class ReviewServiceImplementation implements ReviewService{
    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException {
        return null;
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return List.of();
    }
}
