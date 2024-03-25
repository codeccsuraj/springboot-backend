package com.com.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.com.entity.Users;
import com.com.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/my-profile")
	public ResponseEntity<Users> authenticatedUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Users currentUser = (Users) authentication.getPrincipal();
		return ResponseEntity.ok(currentUser);
	}
	
	@GetMapping("/all-users")
	public ResponseEntity<List<Users>> getAllUser(){
		List <Users> users = userService.getAllUser();
		
		return ResponseEntity.ok(users);
	}
	
}
