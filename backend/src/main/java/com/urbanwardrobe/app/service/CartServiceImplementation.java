package com.urbanwardrobe.app.service;

import com.urbanwardrobe.app.exception.ProductException;
import com.urbanwardrobe.app.model.Cart;
import com.urbanwardrobe.app.model.CartItem;
import com.urbanwardrobe.app.model.User;
import com.urbanwardrobe.app.repository.CartRepository;
import com.urbanwardrobe.app.request.AddItemRequest;

public class CartServiceImplementation implements CartService{
    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService;

    @Override
    public Cart createCart(User user) {
        return null;
    }

    @Override
    public CartItem addCartItem(Long userId, AddItemRequest req) throws ProductException {
        return null;
    }

    @Override
    public Cart findUserCart(Long userId) {
        return null;
    }
}
