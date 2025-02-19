package com.microservice.cart.msvc_cart.service;

import com.microservice.cart.msvc_cart.DTOs.CartWithCredentials;
import com.microservice.cart.msvc_cart.DTOs.ProductDTO;
import com.microservice.cart.msvc_cart.DTOs.UserDTO;
import com.microservice.cart.msvc_cart.clients.UserClient;
import com.microservice.cart.msvc_cart.entities.Cart;
import com.microservice.cart.msvc_cart.entities.CartProduct;
import com.microservice.cart.msvc_cart.entities.CartProductId;
import com.microservice.cart.msvc_cart.entities.CartType;
import com.microservice.cart.msvc_cart.persistence.CartProductRepository;
import com.microservice.cart.msvc_cart.persistence.CartRepository;
import com.microservice.cart.msvc_cart.clients.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Value("${cart.current.day}")
    private String currentDay;

    @Value("${cart.discount.vip}")
    private BigDecimal discountVip;

    @Value("${cart.discount.standard}")
    private BigDecimal discountStandard;

    @Value("${cart.discount.promotional}")
    private BigDecimal discountPromotional;
    
    @Value("${cart.discount.percentage}")
    private BigDecimal discountPercentage;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private UserClient userClient;

    @Override
    public List<Cart> findAll() {
        return (List<Cart>) cartRepository.findAll();
    }

    @Override
    public List<CartProduct> findAllProducts() {
        return (List<CartProduct>) cartProductRepository.findAll();
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(CartWithCredentials cartWithCredentials) {
        UserDTO userDTO = userClient.findByNameAndPassword(cartWithCredentials.getName(), cartWithCredentials.getPassword());
        if (userDTO == null) {
            throw new RuntimeException("Did not find user with this credentials");
        }

        Cart cart = Cart.builder().userId(userDTO.getId()).createdAt(LocalDate.now()).build();

        if (userDTO.isVip()) {
            cart.setCartType(CartType.Vip);
        } else {
            if (cart.getCreatedAt().isEqual(LocalDate.parse(this.currentDay))) {
                cart.setCartType(CartType.Promotional);
            }
        }
        System.out.println(cart);
        cartRepository.save(cart);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public CartProduct addProductToCart(Long cart_id, Long product_id, int quantity) {
        Cart cart = cartRepository.findById(cart_id)
                .orElseThrow(() -> new RuntimeException("Did not find cart with id: " + cart_id));

        ProductDTO productDTO = productClient.findProductById(product_id);
        if (productDTO == null) {
            throw new RuntimeException("Did not find product with id: " + product_id);
        }

        Optional<CartProduct> existingCartProduct = cartProductRepository.findByCartIdAndProductId(cart_id, product_id);

        CartProductId cartProductId = new CartProductId(cart_id, product_id);

        CartProduct cartProduct;
        if (existingCartProduct.isPresent()) {
            cartProduct = existingCartProduct.get();
            cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
        } else {
            cartProduct = new CartProduct();
            cartProduct.setCart(cart);
            cartProduct.setProductId(product_id);
            cartProduct.setQuantity(quantity);
            cartProduct.setId(cartProductId);
        }
        return cartProductRepository.save(cartProduct);
    }

    @Override
    public void removeProductFromCart(Long cart_id, Long product_id, int quantity) {
        cartRepository.findById(cart_id)
                .orElseThrow(() -> new RuntimeException("Cart with id: " + cart_id + " not found"));

        CartProduct cartProduct = cartProductRepository.findByCartIdAndProductId(cart_id, product_id)
                .orElseThrow(() -> new RuntimeException("Product with id: " + product_id + " not found"));

        if (cartProduct.getQuantity() > quantity) {
            cartProduct.setQuantity(cartProduct.getQuantity() - quantity);
            cartProductRepository.save(cartProduct);
        } else {
            cartProductRepository.delete(cartProduct);
        }
    }

    @Override
    public List<CartProduct> findProductsByCartId(Long cartId) {
        return cartProductRepository.findByCartId(cartId);
    }

    @Override
    public void payCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        cart.setPay(true);
        cartRepository.save(cart);
    }

    @Override
    public BigDecimal cost(Long id) {
        BigDecimal discounts = BigDecimal.ZERO;
        boolean percentageDiscount = false;
        Cart cart = cartRepository.findById(id).orElseThrow();
        int productCount = cart.getCartProducts().stream().mapToInt(CartProduct::getQuantity).sum();

        if (productCount > 10) {
            discounts = calculateDiscount(id, cart);
        } else if (productCount == 4){
            percentageDiscount = true;
        }

        BigDecimal cost = calculateCost(cart);
        System.out.println(cost);

        if (percentageDiscount) {
            cost = cost.multiply(discountPercentage);
        } else if (discounts.signum() > 0) {
            cost = cost.subtract(discounts);
        }
        System.out.println(cost);
        return cost;
    }

    private BigDecimal calculateDiscount(Long id, Cart cart) {
        BigDecimal discounts = BigDecimal.ZERO;
        switch (cart.getCartType()) {
            case Vip:
                Long cheapestProductId = this.getCheapestProductId(this.findProductsByCartId(id));
                CartProduct cartProduct = cartProductRepository.findByCartIdAndProductId(id, cheapestProductId).orElseThrow();
                cartProduct.setQuantity(cartProduct.getQuantity() - 1);
                cartProductRepository.save(cartProduct);
                discounts = discounts.add(discountVip);
                break;
            case Standard:
                discounts = discounts.add(discountStandard);
                break;
            case Promotional:
                discounts = discounts.add(discountPromotional);
                break;
        }
        return discounts;
    }

    private Long getCheapestProductId(List<CartProduct> cartProducts) {
        return cartProducts.stream()
                .map(cartProduct -> productClient.findProductById(cartProduct.getProductId()))
                .min(Comparator.comparing(ProductDTO::getPrice))
                .map(ProductDTO::getId)
                .orElseThrow(() -> new RuntimeException("Do not find any valid product"));
    }

    private BigDecimal calculateCost(Cart cart) {
        return cart.getCartProducts().stream()
                .map(cartProduct -> {
                    ProductDTO productDTO = productClient.findProductById(cartProduct.getProductId());

                    return (productDTO != null)
                            ? productDTO.getPrice().multiply(BigDecimal.valueOf(cartProduct.getQuantity()))
                            : BigDecimal.ZERO;
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
