package com.events.tickets.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class utilisConfig {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
