package com.urbanwardrobe.app.service;

import com.urbanwardrobe.app.exception.CartItemException;
import com.urbanwardrobe.app.exception.UserException;
import com.urbanwardrobe.app.model.Cart;
import com.urbanwardrobe.app.model.CartItem;
import com.urbanwardrobe.app.model.Product;
import com.urbanwardrobe.app.repository.CartItemRepository;
import com.urbanwardrobe.app.repository.CartRepository;

public class CartItemServiceImplementation implements CartItemService{
    private CartItemRepository cartItemRepository;
    private UserService userService;
    private CartRepository cartRepository;
    @Override
    public CartItem createCartItem(CartItem cartItem) {
        return null;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        return null;
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        return null;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {

    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        return null;
    }
}
