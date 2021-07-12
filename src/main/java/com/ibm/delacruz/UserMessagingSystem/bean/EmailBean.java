package com.ibm.delacruz.UserMessagingSystem.bean;
import java.time.LocalDate;

import com.ibm.delacruz.UserMessagingSystem.domain.Email;


public class EmailBean {
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
			this.dateSent = LocalDate.now();
		}
		public LocalDate getDateReceived() {
			return null;
		}
		public void setDateReceived(LocalDate dateReceived) {
			this.dateReceived = dateReceived;
		}
		
		public Email convertToEmail() {
			Email email = new Email();
			email.setDateReceived(dateReceived);
			email.setDateSent(LocalDate.now());
			email.setMessage(message);
			email.setSender(sender);
			email.setRecipient(recipient);
			email.setSubject(subject);
			return email;
		}
		

}
