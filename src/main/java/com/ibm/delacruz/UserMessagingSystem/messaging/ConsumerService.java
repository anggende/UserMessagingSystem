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
import com.ibm.delacruz.UserMessagingSystem.repository.UserRepository;
@Service
public class ConsumerService {
	@Autowired
	private EmailRepository emailRepository;
	@Autowired
	private UserRepository userRepository;
	
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
		if (ACTION.equals("createUser")) {
			userRepository.save(user);
		} else if (ACTION.equals("updateUser")) {
			emailRepository.updateSender(user.getEmail(),userRepository.getUserById(user.getId()).getEmail());
			emailRepository.updateRecipient(user.getEmail(),userRepository.getUserById(user.getId()).getEmail());
			userRepository.save(user);
		} else if (ACTION.equals("deleteUser")) {
			emailRepository.updateSender("DELETED",user.getEmail());
			emailRepository.updateRecipient("DELETED",user.getEmail());
			userRepository.deleteById(user.getId());
		} else {
			System.out.println("Invalid action");
		}
	}
	

}
