package com.events.tickets.servicesImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.events.tickets.entities.Event;
import com.events.tickets.exceptions.ResourceAlreadyExists;
import com.events.tickets.exceptions.ResourceNotFound;
import com.events.tickets.payload.EventDto;
import com.events.tickets.payload.EventResponsePaginationObject;
import com.events.tickets.repositories.EventRepository;
import com.events.tickets.services.EventService;

@Service
public class EventServiceImpl implements EventService {

	private EventRepository eventRepository;
	private ModelMapper mapper;

	public EventServiceImpl(EventRepository eventRepository, ModelMapper mapper) {
		this.eventRepository = eventRepository;
		this.mapper = mapper;
	}

	private EventDto mapEventToDto(Event event) {
		return mapper.map(event, EventDto.class);
	}

	private Event mapToEventEntity(EventDto eventDto) {
		return mapper.map(eventDto, Event.class);
	}

	@Override
	public EventDto createEvent(EventDto event) {
		if (eventRepository.existsByTitle(event.getTitle())) {
			throw new ResourceAlreadyExists("Event", "title", event.getTitle());
		}
		Event toSave = mapToEventEntity(event);
		Event saved = eventRepository.save(toSave);
		return mapEventToDto(saved);
	}

	@Override
	public EventResponsePaginationObject getAllEvents(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Event> events = eventRepository.findAll(pageable);
		List<Event> listOfEvents = events.getContent();
		List<EventDto> content = listOfEvents.stream().map(e -> mapEventToDto(e)).collect(Collectors.toList());
		return new EventResponsePaginationObject(content, events.getNumber(), events.getSize(),
				events.getTotalElements(), events.getTotalPages(), events.isLast());
	}

	private Event findEventByID(long id) {
		return eventRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Event", "id", Long.toString(id)));
	}

	@Override
	public EventDto getEventById(long id) {

		Event found = findEventByID(id);
		return mapEventToDto(found);
	}

	@Override
	public EventDto updateEvent(EventDto eventToUpdate, long id) {
		Event found = findEventByID(id);
//		if (eventRepository.existsByTitle(eventToUpdate.getTitle())) {
//			throw new ResourceAlreadyExists("Event", "title", eventToUpdate.getTitle());
//		}
		//mapper.map(eventToUpdate, found);
		found.setDescription(eventToUpdate.getDescription());
		found.setImage(eventToUpdate.getImage());
		found.setTitle(eventToUpdate.getTitle());
		found.setEventDate(eventToUpdate.getEventDate());
		found.setEventTime(eventToUpdate.getEventTime());
		found.setVenueName(eventToUpdate.getVenueName());
		found.setCountry(eventToUpdate.getCountry());
		found.setZipCode(eventToUpdate.getZipCode());
		found.setState(eventToUpdate.getState());
		found.setCapacity(eventToUpdate.getCapacity());
		found.setEndTime(eventToUpdate.getEndTime());
		found.setEarlyAccessTicketsCapacity(eventToUpdate.getEarlyAccessTicketsCapacity());
		found.setVIPTicketsCapacity(eventToUpdate.getVIPTicketsCapacity());
		found.setSimpleTicketCapacity(eventToUpdate.getSimpleTicketCapacity());
		Event updatedEvent = eventRepository.save(found);

		return mapEventToDto(updatedEvent);
	}

	@Override
	public void deleteEvent(long id) {
		Event found = findEventByID(id);
		eventRepository.delete(found);
	}

}
