package com.microservice.product.msvc_product.persistence;

import com.microservice.product.msvc_product.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
