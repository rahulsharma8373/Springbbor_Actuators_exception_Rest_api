package com.rahul.actuators.exception.restapi.boot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@ComponentScan("com.rahul.actuators.exception.restapi.boot")
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot REST API Documentation",
				description = "Spring Boot REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Rahul",
						email = "rahul.net@gmail.com",
						url = "https://www.rahul.net"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.rahul.net/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot User Management Documentation",
				url = "https://www.rahul.net/user_management.html"
		)
)
public class SpringbootActuatorsRestApiApplication {

@Bean
public ModelMapper modelMapper(){
	return new ModelMapper();
}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootActuatorsRestApiApplication.class, args);


	}


	
}

