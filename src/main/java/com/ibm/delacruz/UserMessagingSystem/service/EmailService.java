package com.ibm.delacruz.UserMessagingSystem.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.delacruz.UserMessagingSystem.domain.Email;
import com.ibm.delacruz.UserMessagingSystem.messaging.ConsumerService;

public class EmailService {
	@Autowired
	private ConsumerService consumerService;
	
	public void sendMessage(String subject,String message,String senderEmail,String receiverEmail) {
		Email email = new Email();
		email.setDateSent(LocalDate.now());
		email.setDateReceived(null);
		email.setSubject(subject);
		email.setMessage(message);
	}
}
