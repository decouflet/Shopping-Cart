package com.microservice.cart.msvc_cart.DTOs;

import com.microservice.cart.msvc_cart.entities.CartProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {

    private Long user_id;

    private List<CartProduct> cartProducts;

    private LocalDateTime createdAt;
}
