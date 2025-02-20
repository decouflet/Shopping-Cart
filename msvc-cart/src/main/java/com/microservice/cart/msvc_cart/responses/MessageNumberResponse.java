package com.microservice.cart.msvc_cart.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class MessageNumberResponse {
    boolean error;
    String message;
    BigDecimal value;
}
