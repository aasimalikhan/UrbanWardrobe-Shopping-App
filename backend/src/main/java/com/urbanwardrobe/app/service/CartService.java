package com.urbanwardrobe.app.service;

import com.urbanwardrobe.app.exception.ProductException;
import com.urbanwardrobe.app.model.Cart;
import com.urbanwardrobe.app.model.CartItem;
import com.urbanwardrobe.app.model.User;
import com.urbanwardrobe.app.request.AddItemRequest;

public interface CartService {
    public Cart createCart(User user);

    public CartItem addCartItem(Long userId, AddItemRequest req) throws     ProductException;

    public Cart findUserCart(Long userId);
}
