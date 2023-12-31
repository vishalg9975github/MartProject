package com.asamart.exceptions;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.InternalAuthenticationServiceException;

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
		return new ResponseEntity<>("Innput Field Is Empty", HttpStatus.BAD_REQUEST);
	}

	// @Author Sachin more this is for get
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException elementException) {
		return new ResponseEntity<>("Record Not Found In Database !! ", HttpStatus.NOT_FOUND);
	}

	// @Author Sachin more - this is for in the postman wrong method is selection
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>("Please Change Http Method Type Request", HttpStatus.NOT_FOUND);

	}

	// @Author Sachin more
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<String> handleEmptyResultDataAccessException(
			EmptyResultDataAccessException emptyResultDataAccessException) {
		return new ResponseEntity<>("Data Not Found In Database", HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(InternalAuthenticationServiceException.class)
	public ResponseEntity<String> handleInternalAuthenticationServiceException(
			InternalAuthenticationServiceException internalAuthenticationServiceException) {
		return new ResponseEntity<>("User already exist in DB ", HttpStatus.NOT_ACCEPTABLE);
	}
	// @Author Sachin more
		@ExceptionHandler(NullPointerException.class)
		public ResponseEntity<String> handleNullPointerException(
				NullPointerException nullPointerException) {
			return new ResponseEntity<>("No data In Database", HttpStatus.NOT_FOUND);
		}


}
