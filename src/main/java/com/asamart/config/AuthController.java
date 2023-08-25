package com.asamart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asamart.model.JwtRequest;
import com.asamart.model.JwtResponse;
import com.asamart.model.User;
import com.asamart.security.JwtHelper;
import com.asamart.service.impl.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtHelper jwtHelper;
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

		this.doAuthenticate(request.getEmail(), request.getPassword());

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.jwtHelper.generateToken(userDetails);

		JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void doAuthenticate(String email, String password) {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			authenticationManager.authenticate(authentication);

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}

	}

	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}

	// @PostMapping("/create-user")
	// public User createUser(@RequestBody User user) {
	// return userService.createUser(user);
	// }

	@PostMapping("/create-user")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		try {
			User createdUser = userService.createUser(user);
			return ResponseEntity.ok("You register successfully");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
