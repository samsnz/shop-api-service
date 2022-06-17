package com.shop.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Soft Shop Service API Documentation", version = "1.0", description = "This service is a white-label solution to the existing retailer companies/hotels"))
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

}
