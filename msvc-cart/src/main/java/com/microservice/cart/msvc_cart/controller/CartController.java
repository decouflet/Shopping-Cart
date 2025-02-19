package com.microservice.cart.msvc_cart.controller;


import com.microservice.cart.msvc_cart.DTOs.CartWithCredentials;
import com.microservice.cart.msvc_cart.entities.Cart;
import com.microservice.cart.msvc_cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/create")
    public String saveCart(@RequestBody CartWithCredentials cartWithCredentials) {
        cartService.save(cartWithCredentials);
        return "Cart created successfully and was added to the user: " + cartWithCredentials.getName();
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
    public String deleteById(@RequestParam("id") Long id){
        cartService.deleteById(id);
        return "Cart deleted successfully";
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

    @PutMapping("/pay")
    public String pay(@RequestParam Long id_cart) {
        cartService.payCart(id_cart);
        return "Cart paid successfully";
    }

    @GetMapping("/cost")
    public BigDecimal cost(@RequestParam Long id_cart) {
        return cartService.cost(id_cart);
    }
}
