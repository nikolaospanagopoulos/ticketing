package com.events.tickets.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.events.tickets.payload.ApiResponse;
import com.events.tickets.payload.TicketDto;
import com.events.tickets.services.TicketService;
import com.events.tickets.utilis.ApplicationConstants;

@RestController
@RequestMapping("/api/")
public class TicketController {
	private TicketService ticketService;

	public TicketController(TicketService ticketService) {
		super();
		this.ticketService = ticketService;
	}

	@GetMapping("/events/{eventId}/tickets/{ticketId}")
	public ResponseEntity<ApiResponse> getTicketForEventById(@PathVariable(value = "eventId") long eventId,
			@PathVariable(value = "ticketId") long ticketId) {
		ApiResponse apiResponse = new ApiResponse(this.ticketService.getTicketById(eventId, ticketId));
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PostMapping("/events/{eventId}/tickets")
	public ResponseEntity<ApiResponse> createTicket(@PathVariable(value = "eventId") long eventId,
			@RequestBody TicketDto ticketDto) {
		ApiResponse apiResponse = new ApiResponse(this.ticketService.createTicket(eventId, ticketDto));
		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/events/{eventId}/tickets/{ticketId}")
	public ResponseEntity<ApiResponse> updateTicket(@RequestBody TicketDto toUpdate,
			@PathVariable(value = "eventId") long eventId, @PathVariable(value = "ticketId") long ticketId) {
		ApiResponse apiResponse = new ApiResponse(this.ticketService.updateTicket(toUpdate, eventId, ticketId));
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/events/{eventId}/tickets/{ticketId}")
	public ResponseEntity<ApiResponse> deleteTicketByEventId(@PathVariable(value = "eventId") long eventId,
			@PathVariable(value = "ticketId") long ticketId) {
		this.ticketService.deleteTicket(eventId, ticketId);
		return new ResponseEntity<>(new ApiResponse("Deleted successfully"), HttpStatus.OK);
	}

	@GetMapping("/events/{eventId}/tickets")
	public ResponseEntity<ApiResponse> getAllTicketsForEvent(
			@RequestParam(value = "pageNo", defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = ApplicationConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = ApplicationConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
			@PathVariable(value = "eventId") long eventId) {
		ApiResponse apiResponse = new ApiResponse(
				this.ticketService.getTicketsByEventId(eventId, pageNo, pageSize, sortBy, sortDir));
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
}
