package com.events.tickets.services;

import jakarta.mail.MessagingException;

public interface EmailService {
	void sendTicketEmail(String to, byte[] qrCode) throws MessagingException;
}
