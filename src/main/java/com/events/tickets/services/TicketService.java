package com.events.tickets.services;

import com.events.tickets.payload.PaymentDto;
import com.events.tickets.payload.TicketDto;
import com.events.tickets.payload.TicketResponsePaginationObject;

public interface TicketService {
	TicketDto createTicket(long eventId, TicketDto ticket);

	TicketResponsePaginationObject getTicketsByEventId(long eventId, int pageNo, int pageSize, String sortBy,
			String sortDir);

	TicketDto getTicketById(long eventId, long ticketId);

	void deleteTicket(long eventId, long ticketId);

	TicketDto updateTicket(TicketDto toUpdate, long eventId, long ticketId);

	TicketDto buyTicket(long eventId, long ticketId, PaymentDto paymentDto);
}
