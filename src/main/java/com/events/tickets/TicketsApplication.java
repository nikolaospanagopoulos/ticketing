package com.events.tickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring Boot Ticketing Application", description = "Ticketing Api Documentation", 
version = "v0.1", contact = @Contact(name = "Nikos Panagopoulos", email = "nikos4222@outlook.com.gr", 
url = "https://nikos-panagopoulos.vercel.app/")),
externalDocs = @ExternalDocumentation(
		description = "Github Repository",url = "https://github.com/nikolaospanagopoulos/ticketing"
		))
public class TicketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketsApplication.class, args);
	}

}
