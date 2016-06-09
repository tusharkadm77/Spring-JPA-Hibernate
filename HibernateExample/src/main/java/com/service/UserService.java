package com.service;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.User;
import com.repository.UserRepository;

@Component
public class UserService {

	//@Autowired
	//private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public void performOperation() {
		
		this.save();
	}
	
	
	private User save() {
		User user = new User();
		user.setName("user_2");
		user.setEmail("user2gmail.com");
		
		/*// Create EntityManger
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		//Save user
		entityManager.persist(user);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();*/
		
		userRepository.save(user);
		
		
		return user;
	}
	
}
