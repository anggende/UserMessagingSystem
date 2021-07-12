package com.ibm.delacruz.UserMessagingSystem.messaging;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.ibm.delacruz.UserMessagingSystem.domain.User;
import com.ibm.delacruz.UserMessagingSystem.domain.UserWrapper;
import com.ibm.delacruz.UserMessagingSystem.repository.EmailRepository;
@Service
public class ConsumerService {
	@Autowired
	private EmailRepository repository;
	
	private String removeQuotesAndUnescape(String uncleanJson) {
	    String noQuotes = uncleanJson.replaceAll("^\"|\"$", "");
	    return StringEscapeUtils.unescapeJava(noQuotes);
	}
	
	@KafkaListener(topics="userevents",groupId="user-consumer")
	public void consume(String userJson) throws JsonMappingException, JsonProcessingException {
		UserWrapper userWrapper = new Gson().fromJson(removeQuotesAndUnescape(userJson), UserWrapper.class);
		System.out.println(userWrapper);
		User user = userWrapper.getUser();
		String ACTION = userWrapper.getAction();
		Long id = userWrapper.getId();
		if (ACTION.equals("createUser")) {
			repository.save(user);
		} else if (ACTION.equals("updateUser")) {
			User updatedUser = new User();
			updatedUser.setId(userWrapper.getId());
			updatedUser.setFirstName(user.getFirstName());
			updatedUser.setLastName(user.getLastName());
			updatedUser.setUsername(user.getUsername());
			updatedUser.setEmail(user.getEmail());
			updatedUser.setPassword(user.getPassword());
			updatedUser.setBirthday(user.getBirthday());
			repository.updateSender(updatedUser.getEmail(),repository.getUserById(id).getEmail());
			repository.updateRecipient(updatedUser.getEmail(),repository.getUserById(id).getEmail());
			repository.save(updatedUser);
		} else if (ACTION.equals("deleteUser")) {
			repository.updateSender("DELETED",userWrapper.getUser().getEmail());
			repository.updateRecipient("DELETED",userWrapper.getUser().getEmail());
			repository.deleteUserById(id);
		} else {
			System.out.println("Invalid action");
		}
	}
	

}
