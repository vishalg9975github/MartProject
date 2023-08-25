package com.asamart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.asamart.model.User;
import com.asamart.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> getUser() {
		return userRepository.findAll();

	}

	public User createUser(User user) {
		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exists");
		}

		if (userRepository.findByName(user.getName()).isPresent()) {
			throw new RuntimeException("Name already exists");
		}

		user.setUserId(0);
		if (user.getPassword() != null) {
			// Encode the password before saving
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}

		return userRepository.save(user);

	}

}
