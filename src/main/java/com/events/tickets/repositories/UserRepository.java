package com.events.tickets.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.tickets.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	Optional<User> findByUsername(String username);

	Optional<User> findByUsernameOrEmail(String username, String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
