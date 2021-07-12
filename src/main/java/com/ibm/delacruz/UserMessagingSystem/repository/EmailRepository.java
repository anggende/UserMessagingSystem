package com.ibm.delacruz.UserMessagingSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.delacruz.UserMessagingSystem.domain.Email;
import com.ibm.delacruz.UserMessagingSystem.domain.User;
@Repository("EmailRepository")
public interface EmailRepository extends CrudRepository<Email,User>{

	@Transactional
	@Query("FROM User n WHERE n.id = ?1")
	User getUserById(Long id);
	@Transactional
	@Modifying
	@Query("DELETE FROM User n WHERE n.id = ?1")
	void deleteUserById(Long id);
	@Transactional
	@Modifying
	@Query("UPDATE FROM Email n SET n.sender = ?1 WHERE n.sender = ?2")
	void updateSender(String updatedSender,String sender);
	@Transactional
	@Modifying
	@Query("UPDATE FROM Email n SET n.recipient = ?1 WHERE n.recipient = ?2")
	void updateRecipient(String updatedRecipient,String recipient);
	
	void save(User user);
	//User findByEmail(String email);
	List<Email> findByRecipient(String recipient);
	List<Email> findBySender(String sender);
	

}
