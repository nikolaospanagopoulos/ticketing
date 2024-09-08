package com.events.tickets.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;

import com.events.tickets.entities.User;
import com.events.tickets.exceptions.UserNotFound;
import com.events.tickets.repositories.UserRepository;

@Service
public class CustomUsersDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public CustomUsersDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UserNotFound {
		User foundUser = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> new UserNotFound("username or email", usernameOrEmail));
		Set<GrantedAuthority> authorities = foundUser.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
		return new org.springframework.security.core.userdetails.User(foundUser.getUsername(), foundUser.getPassword(),
				authorities);
	}

}
