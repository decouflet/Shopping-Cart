package com.microservice.product.msvc_product.service;

import com.microservice.product.msvc_product.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    void save(Product product);
    void deleteById(Long id);
}
