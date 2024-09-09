package com.events.tickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.events.tickets.repositories.UserRepository;
import com.google.zxing.client.j2se.CommandLineRunner;

@SpringBootApplication
public class TicketsApplication {
	 
	public static void main(String[] args) {
		SpringApplication.run(TicketsApplication.class, args);
	}

}
