package com.microservice.cart.msvc_cart.clients;

import com.microservice.cart.msvc_cart.DTOs.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-user", url = "localhost:8070")
public interface UserClient {

    @GetMapping("/api/user/search-from-cart")
    UserDTO findByNameAndPassword(@RequestParam("name") String name,@RequestParam("password") String password);
}
