package com.ibm.delacruz.UserMessagingSystem.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Email")
public class Email {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String subject;
	private String sender;
	private String recipient;
	private String message;
	private LocalDate dateSent;
	private LocalDate dateReceived;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDate getDateSent() {
		return dateSent;
	}
	public void setDateSent(LocalDate dateSent) {
		this.dateSent = dateSent;
	}
	public LocalDate getDateReceived() {
		return dateReceived;
	}
	public void setDateReceived(LocalDate dateReceived) {
		this.dateReceived = dateReceived;
	}
	
	
}
