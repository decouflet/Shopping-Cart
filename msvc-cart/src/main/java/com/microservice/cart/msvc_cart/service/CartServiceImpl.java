package com.microservice.cart.msvc_cart.service;

import com.microservice.cart.msvc_cart.entities.Cart;
import com.microservice.cart.msvc_cart.entities.CartProduct;
import com.microservice.cart.msvc_cart.persistence.CartProductRepository;
import com.microservice.cart.msvc_cart.persistence.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Override
    public List<Cart> findAll() {
        return (List<Cart>) cartRepository.findAll();
    }

    @Override
    public List<CartProduct> findAllProducts() {
        return (List<CartProduct>) cartProductRepository.findAll();
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public CartProduct addProductToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Did not find cart with id: " + cartId));

        // Verificar si el producto existe a traves de una llamada de productClient

        Optional<CartProduct> existingCartProduct = cartProductRepository.findByCartIdAndProductId(cartId, productId);

        if (existingCartProduct.isPresent()) {
            CartProduct cartProduct = existingCartProduct.get();
            cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
            return cartProductRepository.save(cartProduct);
        } else {
            CartProduct cartProduct = new CartProduct();
            cartProduct.setCart(cart);
            cartProduct.setProductId(productId);
            cartProduct.setQuantity(quantity);
            return cartProductRepository.save(cartProduct);
        }
    }

    @Override
    public void removeProductFromCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart with id: " + cartId + " not found"));

        CartProduct cartProduct = cartProductRepository.findByCartIdAndProductId(cartId, productId)
                .orElseThrow(() -> new RuntimeException("Product with id: " + productId + " not found"));

        if (cartProduct.getQuantity() > quantity) {
            cartProduct.setQuantity(cartProduct.getQuantity() - quantity);
            cartProductRepository.save(cartProduct);
        } else {
            cartProductRepository.delete(cartProduct);
        }
    }

    @Override
    public List<CartProduct> findProductsByCartId(Long cartId) {
        return cartProductRepository.findByCartId(cartId);
    }
}
