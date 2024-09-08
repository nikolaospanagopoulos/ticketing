package com.events.tickets.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.events.tickets.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	Page<Ticket> findByEventId(long eventId, Pageable pageable);
}
