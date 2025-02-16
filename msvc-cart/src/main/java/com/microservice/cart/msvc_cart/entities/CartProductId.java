package com.microservice.cart.msvc_cart.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CartProductId implements Serializable {
    private Long cartId;
    private Long productId;

    // Equals & HashCode
}

