package com.urbanwardrobe.app.controller;

import com.urbanwardrobe.app.exception.ProductException;
import com.urbanwardrobe.app.exception.UserException;
import com.urbanwardrobe.app.model.Rating;
import com.urbanwardrobe.app.model.User;
import com.urbanwardrobe.app.request.RatingRequest;
import com.urbanwardrobe.app.service.RatingService;
import com.urbanwardrobe.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private UserService userService;
    private RatingService ratingService;

    public RatingController(UserService userService,RatingService ratingServices) {
        this.ratingService=ratingServices;
        this.userService=userService;
        // TODO Auto-generated constructor stub
    }

    @PostMapping("/create")
    public ResponseEntity<Rating> createRatingHandler(@RequestBody RatingRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException, UserException, ProductException {
        User user=userService.findUserProfileByJwt(jwt);
        Rating rating=ratingService.createRating(req, user);
        return new ResponseEntity<>(rating, HttpStatus.ACCEPTED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductsReviewHandler(@PathVariable Long productId){

        List<Rating> ratings=ratingService.getProductsRating(productId);
        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }
}
