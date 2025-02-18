package com.microservice.cart.msvc_cart.persistence;

import com.microservice.cart.msvc_cart.entities.Cart;
import com.microservice.cart.msvc_cart.entities.CartProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
}
