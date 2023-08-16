package com.asamart.exceptions;

import org.springframework.http.HttpStatus;

public class SubCategoryException { 
	
	  
	private final String message;
	private final HttpStatus httpStatus;
	
	public SubCategoryException(String message, HttpStatus httpStatus) {
	
		this.message = message;
		this.httpStatus = httpStatus; 
	}
	
	public String getMessage() {
		return message;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	

	
}
