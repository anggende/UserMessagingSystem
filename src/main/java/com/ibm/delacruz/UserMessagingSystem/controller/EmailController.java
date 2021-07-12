package com.ibm.delacruz.UserMessagingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.delacruz.UserMessagingSystem.bean.EmailBean;
import com.ibm.delacruz.UserMessagingSystem.domain.Email;
import com.ibm.delacruz.UserMessagingSystem.service.EmailService;

@RestController
public class EmailController {

	private EmailService emailService;
	
	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@PostMapping(value="/email/send",consumes="application/json",produces="application/json")
	public String sendEmail(@RequestBody EmailBean emailBean) {
		emailService.sendMessage(emailBean.convertToEmail());
		return "Email sent";
	}
	
	@GetMapping(value="/email/inbox",produces="application/json")
	public List<Email> readMessage(@RequestParam String email) {
		return emailService.readMessage(email);
	}
	
	@GetMapping(value="/email/sent",produces="application/json")
	public List<Email> readSentMessage(@RequestParam String email) {
		return emailService.readSentMessage(email);
	}
}
