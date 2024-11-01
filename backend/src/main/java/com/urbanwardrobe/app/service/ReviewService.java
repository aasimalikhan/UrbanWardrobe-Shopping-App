package com.urbanwardrobe.app.service;

import com.urbanwardrobe.app.exception.ProductException;
import com.urbanwardrobe.app.model.Review;
import com.urbanwardrobe.app.model.User;
import com.urbanwardrobe.app.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    public Review createReview(ReviewRequest req, User user) throws ProductException;

    public List<Review> getAllReview(Long productId);
}
