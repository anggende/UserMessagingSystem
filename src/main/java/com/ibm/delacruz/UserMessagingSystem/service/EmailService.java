package com.ibm.delacruz.UserMessagingSystem.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.delacruz.UserMessagingSystem.domain.Email;
import com.ibm.delacruz.UserMessagingSystem.repository.EmailRepository;

@Service
public class EmailService {
	@Autowired
	private EmailRepository repository;
	
	public void sendMessage(Email email) {
		repository.save(email);
	}

	public List<Email> readMessage(String email) {
		/*List<Email> receivedEmail = repository.findByRecipient(email);
		java.util.Iterator<Email> iterator = receivedEmail.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator);
		}*/
		return repository.findBySender(email);
	}
	
	public List<Email> readSentMessage(String email) {
		return repository.findBySender(email);
	}
	
	/*public User login(String email, String password) {
		if(repository.findByEmail(email)!=null){
			User user = repository.findByEmail(email);
			if (user.getPassword().equals(password)) {
				return user;
			}
		} return null;
	}*/
}
