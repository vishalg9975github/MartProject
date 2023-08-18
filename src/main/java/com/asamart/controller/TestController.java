package com.asamart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.asamart.model.SubCategory;
import com.asamart.service.TestService;

@RestController
@RequestMapping("/Test")
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	private TestService testService;

	@GetMapping("/hello")
	public String printMessage(String message) {
		logger.info("In the Controller class,printMessage method");
		return "Hello world";
	}

}
