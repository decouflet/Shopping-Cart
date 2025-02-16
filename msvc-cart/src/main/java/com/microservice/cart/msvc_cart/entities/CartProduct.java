package com.microservice.cart.msvc_cart.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cart_product")
public class CartProduct {

    @EmbeddedId
    private CartProductId id = new CartProductId();

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(insertable=false, updatable=false)
    private Long productId;  // Solo guarda el ID del producto, sin relación con `Product`

    private int quantity;

}
