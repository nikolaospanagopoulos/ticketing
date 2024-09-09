package com.events.tickets.utilis;

import java.time.Instant;

public class TicketUtilis {
	public static String generateUniqueTicketId(Long eventId, Long userId) {
		return "TICKET-" + eventId + "-" + userId + "-" + Instant.now().toEpochMilli();
	}
}
