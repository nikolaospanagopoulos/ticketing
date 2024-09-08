package com.events.tickets.servicesImpl;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.events.tickets.entities.Role;
import com.events.tickets.entities.User;
import com.events.tickets.exceptions.UserAlreadyExists;
import com.events.tickets.payload.LoginDto;
import com.events.tickets.payload.RegisterDto;
import com.events.tickets.repositories.RoleRepository;
import com.events.tickets.repositories.UserRepository;
import com.events.tickets.security.JwtTokenProvider;
import com.events.tickets.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private ModelMapper modelMapper;
	private JwtTokenProvider jwtTokenProvider;

	public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository roleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper,
			JwtTokenProvider jwtTokenProvider) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public String login(LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenProvider.generateToken(authentication);
		return token;
	}

	@Override
	public String register(RegisterDto registerDto) {
		if (userRepository.existsByUsername(registerDto.getUsername())) {
			throw new UserAlreadyExists("username", registerDto.getUsername());
		}
		if (userRepository.existsByEmail(registerDto.getEmail())) {
			throw new UserAlreadyExists("email", registerDto.getEmail());
		}
		User newUser = modelMapper.map(registerDto, User.class);
		newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		System.out.println(newUser);
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER").get();
		roles.add(userRole);
		newUser.setRoles(roles);
		userRepository.save(newUser);
		return "User registered successfully";
	}

}
