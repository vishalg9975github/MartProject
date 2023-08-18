package com.asamart.exceptions;

import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
//@ControllerAdvice
public class ProductException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public ProductException(String errorMsg) {
		super(errorMsg);
	}
	
}
