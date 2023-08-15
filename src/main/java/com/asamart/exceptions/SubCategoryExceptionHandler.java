package com.asamart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class SubCategoryExceptionHandler {
	
	@ExceptionHandler(value= {SubCategoryNotFoundException.class})
	public ResponseEntity<Object > handlesubCategoryNotFoundException(SubCategoryNotFoundException subCategoryNotFoundException){
		SubCategoryException subCategoryException=new SubCategoryException(subCategoryNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
		return new ResponseEntity<> (subCategoryException,HttpStatus.NOT_FOUND) ;
		
	}
	

}
