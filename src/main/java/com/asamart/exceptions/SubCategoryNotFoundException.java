 package com.asamart.exceptions;

public class SubCategoryNotFoundException extends RuntimeException  {

	private static final long serialVersionUID = 1L;

	public SubCategoryNotFoundException(String message) {
		super(message);
		  
	}
}
