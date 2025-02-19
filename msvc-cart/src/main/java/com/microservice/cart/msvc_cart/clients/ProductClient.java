package com.microservice.cart.msvc_cart.clients;

import com.microservice.cart.msvc_cart.DTOs.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-product", url = "localhost:8090")
public interface ProductClient {

    @GetMapping("/api/product/search-from-cart")
    ProductDTO findProductById(@RequestParam("id") Long id);
}
