package com.events.tickets.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String description;
	private double price;
	private String status;
	private String seatNumber;
	private String section;
	private String transactionId;
	@Column(name = "purchase_date", nullable = true)
	private LocalDate dateOfPurchase;
	@Column(name = "created_date", nullable = false)
	private LocalDate createdDate;
	@Column(name = "unique_identifier", nullable = true)
	private String ticketUniqueIdentifier;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", nullable = false)
	private Event event;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	private String ticketType;

	public Ticket(long id, String title, String description, double price, String status, String seatNumber,
			String section, String transactionId, LocalDate dateOfPurchase, LocalDate createdDate,
			String ticketUniqueIdentifier, Event event, String ticketType) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.status = status;
		this.seatNumber = seatNumber;
		this.section = section;
		this.transactionId = transactionId;
		this.dateOfPurchase = dateOfPurchase;
		this.createdDate = createdDate;
		this.ticketUniqueIdentifier = ticketUniqueIdentifier;
		this.event = event;
		this.ticketType = ticketType;
	}

	public Ticket() {
		super();
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(LocalDate dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getTicketUniqueIdentifier() {
		return ticketUniqueIdentifier;
	}

	public void setTicketUniqueIdentifier(String ticketUniqueIdentifier) {
		this.ticketUniqueIdentifier = ticketUniqueIdentifier;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", status=" + status + ", seatNumber=" + seatNumber + ", section=" + section + ", transactionId="
				+ transactionId + ", dateOfPurchase=" + dateOfPurchase + ", createdDate=" + createdDate
				+ ", ticketUniqueIdentifier=" + ticketUniqueIdentifier + ", event=" + event + ", ticketType="
				+ ticketType + "]";
	}

}
