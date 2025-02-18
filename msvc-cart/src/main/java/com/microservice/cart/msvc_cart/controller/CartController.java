package com.microservice.cart.msvc_cart.controller;


import com.microservice.cart.msvc_cart.DTOs.CartDTO;
import com.microservice.cart.msvc_cart.entities.Cart;
import com.microservice.cart.msvc_cart.entities.CartProduct;
import com.microservice.cart.msvc_cart.service.CartService;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/create")
    public void saveCart(@RequestBody Cart cart) {
        cartService.save(cart);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(cartService.findAll());
    }

    @GetMapping("/all-products")
    public ResponseEntity<?> findAllProducts(){
        return ResponseEntity.ok(cartService.findAllProducts());
    }

    @GetMapping("/all-products-by-cart")
    public ResponseEntity<?> findAllProductsByCart(@RequestParam Long id){
        return ResponseEntity.ok(cartService.findProductsByCartId(id));
    }

    @GetMapping("/search")
    public ResponseEntity<?> findById(@RequestParam("id") Long id){
        return ResponseEntity.ok(cartService.findById(id));
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam("id") Long id){
        cartService.deleteById(id);
    }

    @PutMapping("/add-product")
    public String addProduct(@RequestParam Long id_cart, @RequestParam Long id_product, @RequestParam int quantity) {
        try {
        cartService.addProductToCart(id_cart, id_product, quantity);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Product added successfully";
    }

    @DeleteMapping("/remove-or-delete-product")
    public String removeOrDeleteProduct(@RequestParam Long id_cart,
                                        @RequestParam Long id_product,
                                        @RequestParam int quantity) {
        try {
            cartService.removeProductFromCart(id_cart, id_product, quantity);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Product subtracted or deleted successfully";
    }
}
