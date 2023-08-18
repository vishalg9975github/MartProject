package com.asamart.exceptions;

import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// @Author Sachin more- if we pass the empty input

	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException) {
		return new ResponseEntity<>("input field is empty", HttpStatus.BAD_REQUEST);
	}

	// @Author Sachin more this is for get
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException elementException) {
		return new ResponseEntity<>("No value present in database", HttpStatus.NOT_FOUND);
	}

	// @Author Sachin more - this is for in the postman wrong method is selection
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>("please change http method type request", HttpStatus.NOT_FOUND);

	}

	// @Author Sachin more- delete method
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<String> handleEmptyResultDataAccessException(
			EmptyResultDataAccessException emptyResultDataAccessException) {
		return new ResponseEntity<>("data not found in database", HttpStatus.NOT_FOUND);
	}


}
