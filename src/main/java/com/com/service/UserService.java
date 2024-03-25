package com.com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.com.entity.Users;
import com.com.repositories.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<Users> getAllUser(){
		List<Users> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
}
