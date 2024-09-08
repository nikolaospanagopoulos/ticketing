package com.events.tickets.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.events.tickets.entities.Event;
import com.events.tickets.entities.Ticket;
import com.events.tickets.exceptions.ResourceNotFound;
import com.events.tickets.exceptions.TicketLimitReached;
import com.events.tickets.payload.PaymentDto;
import com.events.tickets.payload.TicketDto;
import com.events.tickets.payload.TicketResponsePaginationObject;
import com.events.tickets.repositories.EventRepository;
import com.events.tickets.repositories.TicketRepository;
import com.events.tickets.services.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	private TicketRepository ticketRepository;
	private EventRepository eventRepository;
	private ModelMapper mapper;

	public TicketServiceImpl(TicketRepository ticketRepository, EventRepository eventRepository, ModelMapper mapper) {
		super();
		this.ticketRepository = ticketRepository;
		this.eventRepository = eventRepository;
		this.mapper = mapper;
	}

	private Event getEventById(long eventId) {
		return eventRepository.findById(eventId)
				.orElseThrow(() -> new ResourceNotFound("Event", "id", Long.toString(eventId)));
	}

	private List<Ticket> findTickets(Event event, String type) {
		return event.getTickets().stream().filter(t -> t.getTicketType().equalsIgnoreCase(type))
				.collect(Collectors.toList());
	}

	@Override
	public TicketDto createTicket(long eventId, TicketDto ticketDto) {

		Ticket ticket = mapper.map(ticketDto, Ticket.class);
		Event event = getEventById(eventId);
		String ticketType = ticket.getTicketType();

		ticket.setEvent(event);
		if (ticketType.equalsIgnoreCase("VIP")) {

			List<Ticket> vipTickets = findTickets(event, ticketType);

			if (vipTickets.size() >= event.getVIPTicketsCapacity()) {
				throw new TicketLimitReached("Ticket", "capacity", Long.toString(event.getVIPTicketsCapacity()));
			}
		}
		if (ticketType.equalsIgnoreCase("EARLY")) {
			List<Ticket> earlyAccess = findTickets(event, ticketType);
			if (earlyAccess.size() >= event.getEarlyAccessTicketsCapacity()) {
				throw new TicketLimitReached("Ticket", "capacity",
						Long.toString(event.getEarlyAccessTicketsCapacity()));
			}
		}
		if (ticketType.equalsIgnoreCase("NORMAL")) {
			List<Ticket> normal = findTickets(event, ticketType);
			if (normal.size() >= event.getSimpleTicketCapacity()) {
				throw new TicketLimitReached("Ticket", "capacity", Long.toString(event.getSimpleTicketCapacity()));
			}
		}
		Ticket saved = ticketRepository.save(ticket);
		return mapper.map(saved, TicketDto.class);
	}

	@Override
	public TicketResponsePaginationObject getTicketsByEventId(long eventId, int pageNo, int pageSize, String sortBy,
			String sortDir) {
		getEventById(eventId);
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending()
				: Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Ticket> ticketsPage = ticketRepository.findByEventId(eventId, pageable);
		List<TicketDto> content = ticketsPage.getContent().stream().map(t -> mapper.map(t, TicketDto.class))
				.collect(Collectors.toList());
		TicketResponsePaginationObject ticketResponsePaginationObject = new TicketResponsePaginationObject(content,
				ticketsPage.getNumber(), ticketsPage.getSize(), ticketsPage.getTotalElements(),
				ticketsPage.getTotalPages(), ticketsPage.isLast());
		return ticketResponsePaginationObject;
	}

	@Override
	public TicketDto updateTicket(TicketDto toUpdate, long eventId, long ticketId) {
		Event event = getEventById(eventId);
		Ticket found = getTicketByEventId(eventId, ticketId);
		if (!found.getEvent().getId().equals(event.getId())) {
			throw new ResourceNotFound("Ticket", "id", Long.toString(ticketId));
		}
		found.setTitle(toUpdate.getTitle());
		found.setDescription(toUpdate.getDescription());
		found.setPrice(toUpdate.getPrice());
		found.setStatus(toUpdate.getStatus());
		found.setSeatNumber(toUpdate.getSeatNumber());
		found.setSection(toUpdate.getSection());
		found.setTransactionId(toUpdate.getTransactionId());
		found.setDateOfPurchase(toUpdate.getDateOfPurchase());
		found.setCreatedDate(toUpdate.getCreatedDate());
		found.setTicketUniqueIdentifier(toUpdate.getTicketUniqueIdentifier());
		found.setTicketType(toUpdate.getTicketType());
		Ticket updatedTicket = ticketRepository.save(found);
		return mapper.map(updatedTicket, TicketDto.class);
	}

	@Override
	public TicketDto getTicketById(long eventId, long ticketId) {
		Event event = getEventById(eventId);
		Ticket found = getTicketByEventId(eventId, ticketId);
		if (!found.getEvent().getId().equals(event.getId())) {
			throw new ResourceNotFound("Ticket", "id", Long.toString(ticketId));
		}
		return mapper.map(found, TicketDto.class);
	}

	private Ticket getTicketByEventId(long eventId, long ticketId) {
		return ticketRepository.findById(ticketId)
				.orElseThrow(() -> new ResourceNotFound("Ticket", "id", Long.toString(ticketId)));
	}

	@Override
	public void deleteTicket(long eventId, long ticketId) {
		Event event = getEventById(eventId);
		Ticket found = getTicketByEventId(eventId, ticketId);

		if (!found.getEvent().getId().equals(event.getId())) {
			throw new ResourceNotFound("Ticket", "id", Long.toString(ticketId));
		}
		ticketRepository.delete(found);
	}

	@Override
	public TicketDto buyTicket(long eventId, long ticketId, PaymentDto paymentDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
