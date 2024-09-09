package com.events.tickets.payload;

import java.time.LocalDate;

public class TicketDto implements DataResponse {
	private Long id;
	private String title;
	private String description;
	private double price;
	private String status;
	private String seatNumber;
	private String section;
	private String transactionId;
	private LocalDate dateOfPurchase;
	private LocalDate createdDate;
	private String ticketUniqueIdentifier;
	private String ticketType;
	private UserDto boughtBy;

	public TicketDto(Long id, String title, String description, double price, String status, String seatNumber,
			String section, String transactionId, LocalDate dateOfPurchase, LocalDate createdDate,
			String ticketUniqueIdentifier, String ticketType, UserDto boughtBy) {
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
		this.ticketType = ticketType;
		this.boughtBy = boughtBy;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TicketDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public UserDto getBoughtBy() {
		return boughtBy;
	}

	public void setBoughtBy(UserDto boughtBy) {
		this.boughtBy = boughtBy;
	}

}
