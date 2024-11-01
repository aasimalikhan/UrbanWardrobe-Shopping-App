package com.urbanwardrobe.app.controller;

import com.urbanwardrobe.app.exception.ProductException;
import com.urbanwardrobe.app.exception.UserException;
import com.urbanwardrobe.app.model.Cart;
import com.urbanwardrobe.app.model.CartItem;
import com.urbanwardrobe.app.model.User;
import com.urbanwardrobe.app.request.AddItemRequest;
import com.urbanwardrobe.app.response.ApiResponse;
import com.urbanwardrobe.app.service.CartService;
import com.urbanwardrobe.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private CartService cartService;
    private UserService userService;

    public CartController(CartService cartService,UserService userService) {
        this.cartService=cartService;
        this.userService=userService;
    }

    @GetMapping("/")
    public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws UserException {

        User user=userService.findUserProfileByJwt(jwt);
        Cart cart=cartService.findUserCart(user.getId());
        System.out.println("cart - "+cart.getUser().getEmail());
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddItemRequest req,
                                                  @RequestHeader("Authorization") String jwt) throws UserException, ProductException, ProductException {

        User user=userService.findUserProfileByJwt(jwt);
        CartItem item = cartService.addCartItem(user.getId(), req);
        ApiResponse res= new ApiResponse("Item Added To Cart Successfully",true);
        return new ResponseEntity<>(item,HttpStatus.ACCEPTED);
    }
}
