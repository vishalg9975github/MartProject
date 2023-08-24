package com.asamart.service.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
		user.setUserId(0);
		if (user.getPassword() != null) {
			// Encode the password before saving
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}

		return userRepository.save(user);

	}

	
}
