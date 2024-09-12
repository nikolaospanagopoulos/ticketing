package com.events.tickets.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.events.tickets.services.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendTicketEmail(String to, byte[] qrCode) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setTo(to);
		helper.setSubject("Your ticket QR code");
		helper.setText("Attached is your ticket QR code.");
		helper.addAttachment("ticket.png", new ByteArrayResource(qrCode));
		javaMailSender.send(mimeMessage);
	}

}
