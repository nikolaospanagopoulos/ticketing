package com.events.tickets.controllers;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.events.tickets.payload.JwtResponseDto;
import com.events.tickets.payload.LoginDto;
import com.events.tickets.payload.RegisterDto;
import com.events.tickets.payload.RoleDto;
import com.events.tickets.payload.UserDetailsDto;
import com.events.tickets.services.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private AuthService authService;

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@GetMapping("/user")
	public ResponseEntity<UserDetailsDto>getUserInfo(Authentication authentication){
		String username = authentication.getName();
		UserDetailsDto userDetails = new UserDetailsDto();
		userDetails.setUsername(username);
		userDetails.setRoles(authentication.getAuthorities().stream().map(a->{
		   RoleDto role = new RoleDto();
		   role.setName(a.getAuthority());
		   return role;
		 }).collect(Collectors.toSet()));
		return new ResponseEntity<>(userDetails,HttpStatus.OK);
	}
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpServletResponse response) {
		Cookie jwtCookie = new Cookie("JWT", null);
		jwtCookie.setHttpOnly(true);
		jwtCookie.setPath("/");
		jwtCookie.setMaxAge(0); // Remove the cookie
		response.addCookie(jwtCookie);

		return ResponseEntity.ok("Logged out successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponseDto> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
		String token = authService.login(loginDto);
		JwtResponseDto jwtResponseDto = new JwtResponseDto();
		jwtResponseDto.setAccessToken(token);
		Cookie jwtCookie = new Cookie("JWT", token);
		jwtCookie.setHttpOnly(true);
		jwtCookie.setPath("/"); // Or specify the path where the cookie is relevant
		jwtCookie.setMaxAge(7 * 24 * 60 * 60); // Expires in 7 days
		response.addCookie(jwtCookie);
		return ResponseEntity.ok(jwtResponseDto);
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
		System.out.println(registerDto.getEmail());
		String response = authService.register(registerDto);
		System.out.println(response);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
