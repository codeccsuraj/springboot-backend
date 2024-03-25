package com.com.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.com.DTO.LoginUserDTO;
import com.com.DTO.UserRegisterDTO; // Import statement for UserRegisterDTO
import com.com.entity.Users;
import com.com.repositories.UserRepository;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public Users signup(UserRegisterDTO input) {
        Users users = new Users();
        users.setFirst_name(input.getFirstName());
        users.setLast_name(input.getLastName());
        users.setEmail(input.getEmail());
        users.setMobile(input.getMobile());
        users.setPassword(passwordEncoder.encode(input.getPassword()));

        try {
            return userRepository.save(users);
        } catch (Exception e) {
            // Handle database save error
            // Log the error for debugging
            throw new RuntimeException("Failed to sign up user", e);
        }
    }
    
    public Users authenticate(LoginUserDTO input) {
    	authenticationManager.authenticate(
    			new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
    	return userRepository.findByEmail(input.getEmail())
    			.orElseThrow();
    }
}
