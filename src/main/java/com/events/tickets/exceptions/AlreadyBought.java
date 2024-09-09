package com.events.tickets.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyBought extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Long ticketId;

	public AlreadyBought(Long ticketId) {
		super(String.format("Ticket with %s : has been already bought", ticketId));
		this.ticketId = ticketId;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
