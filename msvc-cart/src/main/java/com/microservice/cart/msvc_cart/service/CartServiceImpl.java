package com.microservice.cart.msvc_cart.service;

import com.microservice.cart.msvc_cart.DTOs.ProductDTO;
import com.microservice.cart.msvc_cart.entities.Cart;
import com.microservice.cart.msvc_cart.entities.CartProduct;
import com.microservice.cart.msvc_cart.entities.CartProductId;
import com.microservice.cart.msvc_cart.persistence.CartProductRepository;
import com.microservice.cart.msvc_cart.persistence.CartRepository;
import com.microservice.cart.msvc_cart.productClient.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private ProductClient productClient;

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
    public CartProduct addProductToCart(Long cart_id, Long product_id, int quantity) {
        Cart cart = cartRepository.findById(cart_id)
                .orElseThrow(() -> new RuntimeException("Did not find cart with id: " + cart_id));

        ProductDTO productDTO = productClient.findProductById(product_id);
        if (productDTO == null) {
            throw new RuntimeException("Did not find product with id: " + product_id);
        }

        Optional<CartProduct> existingCartProduct = cartProductRepository.findByCartIdAndProductId(cart_id, product_id);

        CartProductId cartProductId = new CartProductId(cart_id, product_id);

        CartProduct cartProduct;
        if (existingCartProduct.isPresent()) {
            cartProduct = existingCartProduct.get();
            cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
        } else {
            cartProduct = new CartProduct();
            cartProduct.setCart(cart);
            cartProduct.setProductId(product_id);
            cartProduct.setQuantity(quantity);
            cartProduct.setId(cartProductId);
        }
        return cartProductRepository.save(cartProduct);
    }

    @Override
    public void removeProductFromCart(Long cart_id, Long product_id, int quantity) {
        cartRepository.findById(cart_id)
                .orElseThrow(() -> new RuntimeException("Cart with id: " + cart_id + " not found"));

        CartProduct cartProduct = cartProductRepository.findByCartIdAndProductId(cart_id, product_id)
                .orElseThrow(() -> new RuntimeException("Product with id: " + product_id + " not found"));

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
