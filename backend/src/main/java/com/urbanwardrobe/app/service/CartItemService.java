package com.urbanwardrobe.app.service;

import com.urbanwardrobe.app.exception.CartItemException;
import com.urbanwardrobe.app.exception.UserException;
import com.urbanwardrobe.app.model.Cart;
import com.urbanwardrobe.app.model.CartItem;
import com.urbanwardrobe.app.model.Product;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

    public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
