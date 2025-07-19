package com.at.t.eCommerce.exception;


public class UserAlreadyExistsException extends RuntimeException{
	
	public UserAlreadyExistsException(String message) {
		super(message);
	}

}
