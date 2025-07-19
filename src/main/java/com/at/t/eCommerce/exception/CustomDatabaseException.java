package com.at.t.eCommerce.exception;

public class CustomDatabaseException extends RuntimeException{
	
	// Constructor with only a custom message
    public CustomDatabaseException(String message) {
        super(message);
    }

    // Constructor with a custom message and the underlying cause
    public CustomDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
