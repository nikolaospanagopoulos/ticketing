package com.events.tickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.tickets.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	boolean existsByTitle(String title);
}
