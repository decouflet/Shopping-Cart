package com.microservice.cart.msvc_cart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Cart cart;

    @Column(insertable=false, updatable=false)
    @JoinColumn(name = "product_id")
    private Long productId;

    private int quantity;

}
