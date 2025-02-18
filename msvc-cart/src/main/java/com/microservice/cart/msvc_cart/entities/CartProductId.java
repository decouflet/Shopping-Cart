package com.microservice.cart.msvc_cart.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Data
public class CartProductId implements Serializable {
    private Long cartId;
    private Long productId;

    // Equals & HashCode
}

