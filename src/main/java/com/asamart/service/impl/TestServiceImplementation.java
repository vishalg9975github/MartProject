package com.asamart.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asamart.controller.TestController;
import com.asamart.model.SubCategory;
import com.asamart.repository.TestRepository;
import com.asamart.service.TestService;

@Service
public class TestServiceImplementation implements TestService {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	private TestRepository testRepository;
	

	@Override
	public String printMessage(String message) {
		logger.info("In the service class,printMessage method");
		return "Hello World";
	}

	
	

}
