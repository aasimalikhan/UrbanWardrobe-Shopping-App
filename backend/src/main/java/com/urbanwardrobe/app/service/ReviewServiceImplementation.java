package com.urbanwardrobe.app.service;

import com.urbanwardrobe.app.exception.ProductException;
import com.urbanwardrobe.app.model.Product;
import com.urbanwardrobe.app.model.Review;
import com.urbanwardrobe.app.model.User;
import com.urbanwardrobe.app.repository.ProductRepository;
import com.urbanwardrobe.app.repository.ReviewRepository;
import com.urbanwardrobe.app.request.ReviewRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService{
    private ReviewRepository reviewRepository;
    private ProductService productService;
    private ProductRepository productRepository;

    public ReviewServiceImplementation(ReviewRepository reviewRepository,ProductService productService,ProductRepository productRepository) {
        this.reviewRepository=reviewRepository;
        this.productService=productService;
        this.productRepository=productRepository;
    }

    @Override
    public Review createReview(ReviewRequest req,User user) throws ProductException {
        // TODO Auto-generated method stub
        Product product=productService.findProductById(req.getProductId());
        Review review=new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());

//		product.getReviews().add(review);
        productRepository.save(product);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {

        return reviewRepository.getAllProductsReview(productId);
    }

}
