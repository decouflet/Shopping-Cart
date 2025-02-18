package com.microservice.product.msvc_product.controller;

import com.microservice.product.msvc_product.entities.Product;
import com.microservice.product.msvc_product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public void saveCart(@RequestBody Product product) {
        productService.save(product);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<?> findById(@RequestParam("id") Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam("id") Long id){
        productService.deleteById(id);
    }

}
