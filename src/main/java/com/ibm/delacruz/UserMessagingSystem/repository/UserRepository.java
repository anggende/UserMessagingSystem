package com.ibm.delacruz.UserMessagingSystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.ibm.delacruz.UserMessagingSystem.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User getUserById(Long id);
	void deleteById(Long id);
}
