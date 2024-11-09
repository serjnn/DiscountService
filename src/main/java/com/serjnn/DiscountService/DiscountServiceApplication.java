package com.serjnn.DiscountService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class DiscountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscountServiceApplication.class, args);
	}

}
