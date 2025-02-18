package com.microservice.product.msvc_product.controller;

import com.microservice.product.msvc_product.entities.Product;
import com.microservice.product.msvc_product.entities.ProductDTO;
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
    public String saveProduct(@RequestBody Product product) {
        productService.save(product);
        return "Product created successfully";
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<?> findById(@RequestParam("id") Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/search-from-cart")
    public ProductDTO findByIdFromCart(@RequestParam("id") Long id){
        try {
            Product product = productService.findById(id);
            ProductDTO productDTO = new ProductDTO();
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            return productDTO;
        } catch (Exception e) {
            return null;
        }

    }

    @DeleteMapping("/delete")
    public String deleteById(@RequestParam("id") Long id){
        productService.deleteById(id);
        return "Product deleted successfully";
    }

}
