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
import com.events.tickets.payload.EventDto;
import com.events.tickets.services.EventService;
import com.events.tickets.utilis.ApplicationConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/events")
@Tag(name = "CRUD REST for Events")

public class EventController {
	private EventService eventService;

	public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	@Operation(summary = "Create Event")
	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
		EventDto created = this.eventService.createEvent(eventDto);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@Operation(summary = "Get All Events")
	@GetMapping
	public ResponseEntity<ApiResponse> getAllEvents(
			@RequestParam(value = "pageNo", defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = ApplicationConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = ApplicationConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		ApiResponse apiResponse = new ApiResponse(eventService.getAllEvents(pageNo, pageSize, sortBy, sortDir));
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	@Operation(summary = "Get Event By Id")
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getEventById(@PathVariable(name = "id") long id) {
		ApiResponse apiResponse = new ApiResponse(this.eventService.getEventById(id));
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@Operation(summary = "Update Event By Id")
	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateEvent(@RequestBody EventDto toUpdate, @PathVariable(name = "id") long id) {
		ApiResponse apiResponse = new ApiResponse(this.eventService.updateEvent(toUpdate, id));
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@SecurityRequirement(name = "Bearer Auth")
	@Operation(summary = "Delete Event By Id")
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteEvent(@PathVariable(name = "id") long id) {
		this.eventService.deleteEvent(id);
		return new ResponseEntity<>(new ApiResponse("Deleted successfully"), HttpStatus.OK);

	}
}
