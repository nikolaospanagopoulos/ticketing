package com.events.tickets.services;

import com.events.tickets.payload.EventDto;
import com.events.tickets.payload.EventResponsePaginationObject;

public interface EventService {
	EventDto createEvent(EventDto event);

	EventResponsePaginationObject getAllEvents(int pageNo, int pageSize, String sortBy, String sortDir);

	EventDto getEventById(long id);

	EventDto updateEvent(EventDto eventToUpdate, long id);

	void deleteEvent(long id);
}
