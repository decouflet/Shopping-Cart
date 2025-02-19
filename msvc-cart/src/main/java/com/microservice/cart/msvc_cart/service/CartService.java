package com.microservice.cart.msvc_cart.service;

import com.microservice.cart.msvc_cart.DTOs.CartWithCredentials;
import com.microservice.cart.msvc_cart.entities.Cart;
import com.microservice.cart.msvc_cart.entities.CartProduct;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    List<Cart> findAll();
    List<CartProduct> findAllProducts();
    Cart findById(Long id);
    void save(CartWithCredentials cartWithCredentials);
    void deleteById(Long id);
    CartProduct addProductToCart(Long cartId, Long productId, int quantity);
    void removeProductFromCart(Long cartId, Long productId, int quantity);
    List<CartProduct> findProductsByCartId(Long cartId);
    void payCart(Long cartId);
    BigDecimal cost(Long cartId);
}
