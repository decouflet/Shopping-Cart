package com.microservice.cart.msvc_cart.persistence;

import com.microservice.cart.msvc_cart.entities.CartProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartProductRepository extends CrudRepository<CartProduct, Long> {
    Optional<CartProduct> findByCartIdAndProductId(Long cartId, Long productId);

    List<CartProduct> findByCartId(Long cartId);
}
