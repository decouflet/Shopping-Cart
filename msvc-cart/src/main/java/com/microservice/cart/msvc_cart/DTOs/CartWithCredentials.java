package com.microservice.cart.msvc_cart.DTOs;

import com.microservice.cart.msvc_cart.entities.CartProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartWithCredentials {
    private String name;
    private String password;
}
