package com.microservice.cart.msvc_cart.controller;


import com.microservice.cart.msvc_cart.DTOs.CartWithCredentials;
import com.microservice.cart.msvc_cart.entities.Cart;
import com.microservice.cart.msvc_cart.responses.MessageNumberResponse;
import com.microservice.cart.msvc_cart.responses.MessageResponse;
import com.microservice.cart.msvc_cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/create")
    public Cart saveCart(@RequestBody CartWithCredentials cartWithCredentials, @RequestParam Long cart_id) {
        if (cart_id > 0) {
            cartService.deleteById(cart_id);
        }
        return cartService.save(cartWithCredentials);
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
    public MessageResponse deleteById(@RequestParam("id") Long id){
        try {
            cartService.deleteById(id);
        } catch (Exception e) {
            return new MessageResponse(true, e.getMessage());
        }
        return new MessageResponse(false, "Cart deleted successfully");
    }

    @PutMapping("/add-product")
    public MessageResponse addProduct(@RequestParam Long id_cart, @RequestParam Long id_product, @RequestParam int quantity) {
        try {
        cartService.addProductToCart(id_cart, id_product, quantity);
        } catch (Exception e) {
            return new MessageResponse(true, e.getMessage());
        }
        return new MessageResponse(false, "Product added sucessfully");
    }

    @DeleteMapping("/remove-or-delete-product")
    public MessageResponse removeOrDeleteProduct(@RequestParam Long id_cart,
                                        @RequestParam Long id_product,
                                        @RequestParam int quantity) {
        try {
            cartService.removeProductFromCart(id_cart, id_product, quantity);
        } catch (Exception e) {
            return new MessageResponse(true, e.getMessage());
        }
        return new MessageResponse(false, "Product subtracted or deleted successfully");
    }

    @PutMapping("/pay")
    public MessageResponse pay(@RequestParam Long id_cart) {
        try {
            cartService.payCart(id_cart);
        } catch (Exception e) {
            return new MessageResponse(true, e.getMessage());
        }

        return new MessageResponse(false, "Cart paid successfully");
    }

    @GetMapping("/cost")
    public MessageNumberResponse cost(@RequestParam Long id_cart) {
        BigDecimal cost = BigDecimal.ZERO;
        try {
            cost = cartService.cost(id_cart);
        } catch (Exception e) {
            return new MessageNumberResponse(true, e.getMessage(), BigDecimal.ZERO);
        }
        return new MessageNumberResponse(false, "Cost calculated su", cost);
    }
}
