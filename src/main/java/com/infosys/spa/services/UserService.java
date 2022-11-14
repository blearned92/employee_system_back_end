package com.infosys.spa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.spa.models.User;
import com.infosys.spa.repositories.UserRepository;

@Service
public class UserService {

	public UserRepository userRepository;
	
	@Autowired
	private void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
	
	public User findByUserName(String userName) {
		return this.userRepository.findByUserName(userName);
	}
	
	public void save(User user) {
		this.userRepository.save(user);
	}
	
	public void delete(User user) {
		this.userRepository.delete(user);
	}
}
