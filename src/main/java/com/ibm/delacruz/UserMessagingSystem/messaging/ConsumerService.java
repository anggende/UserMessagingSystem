package com.ibm.delacruz.UserMessagingSystem.messaging;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.ibm.delacruz.UserMessagingSystem.domain.User;
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
		User user = new Gson().fromJson(removeQuotesAndUnescape(userJson), User.class);
		System.out.println(user);
		repository.save(user);
	}
	

}
