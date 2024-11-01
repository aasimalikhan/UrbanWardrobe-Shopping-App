package com.urbanwardrobe.app.service;

import com.urbanwardrobe.app.exception.ProductException;
import com.urbanwardrobe.app.model.Rating;
import com.urbanwardrobe.app.model.User;
import com.urbanwardrobe.app.request.RatingRequest;

import java.util.List;

public interface RatingService {
    public Rating createRating(RatingRequest req, User user) throws ProductException;

    public List<Rating> getProductsRating(Long productId);
}
