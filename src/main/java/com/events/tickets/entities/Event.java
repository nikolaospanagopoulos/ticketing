package com.events.tickets.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "events", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) })
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private LocalDate eventDate;
	private LocalTime eventTime;
	private String image;
	private String venueName;
	private String country;
	private String zipCode;
	private String state;
	private int capacity;
	private int earlyAccessTicketsCapacity;
	private int VIPTicketsCapacity;
	private int simpleTicketCapacity;

	public int getEarlyAccessTicketsCapacity() {
		return earlyAccessTicketsCapacity;
	}

	public void setEarlyAccessTicketsCapacity(int earlyAccessTicketsCapacity) {
		this.earlyAccessTicketsCapacity = earlyAccessTicketsCapacity;
	}

	public int getVIPTicketsCapacity() {
		return VIPTicketsCapacity;
	}

	public void setVIPTicketsCapacity(int vIPTicketsCapacity) {
		VIPTicketsCapacity = vIPTicketsCapacity;
	}

	public int getSimpleTicketCapacity() {
		return simpleTicketCapacity;
	}

	public void setSimpleTicketCapacity(int simpleTicketCapacity) {
		this.simpleTicketCapacity = simpleTicketCapacity;
	}

	private LocalTime endTime;
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	private Set<Ticket> tickets = new HashSet<Ticket>();

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Event(Long id, String title, String description, LocalDate eventDate, LocalTime eventTime, String image,
			String venueName, String country, String zipCode, String state, int capacity, LocalTime endTime,
			int earlyAccessTicketsCapacity, int VIPTicketsCapacity, int simpleTicketCapacity) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.image = image;
		this.venueName = venueName;
		this.country = country;
		this.zipCode = zipCode;
		this.state = state;
		this.capacity = capacity;
		this.endTime = endTime;
		this.earlyAccessTicketsCapacity = earlyAccessTicketsCapacity;
		this.VIPTicketsCapacity = VIPTicketsCapacity;
		this.simpleTicketCapacity = simpleTicketCapacity;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Event() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public LocalTime getEventTime() {
		return eventTime;
	}

	public void setEventTime(LocalTime eventTime) {
		this.eventTime = eventTime;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
