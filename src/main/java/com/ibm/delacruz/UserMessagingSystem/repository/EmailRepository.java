package com.ibm.delacruz.UserMessagingSystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.ibm.delacruz.UserMessagingSystem.domain.Email;
import com.ibm.delacruz.UserMessagingSystem.domain.User;

public interface EmailRepository extends CrudRepository<Email,User>{

	void save(User user);
}
