package com.events.tickets.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.tickets.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
