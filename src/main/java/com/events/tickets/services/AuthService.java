package com.events.tickets.services;

import com.events.tickets.payload.LoginDto;
import com.events.tickets.payload.RegisterDto;

public interface AuthService {
	String login(LoginDto loginDto);

	String register(RegisterDto registerDto);
}
