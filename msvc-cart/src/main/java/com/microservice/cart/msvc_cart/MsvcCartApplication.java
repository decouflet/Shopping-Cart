package com.microservice.cart.msvc_cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcCartApplication.class, args);
	}

}
