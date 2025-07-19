package com.at.t.eCommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.at.t.eCommerce.util.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Handle UserAlreadyExistsException
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}

	// Handle RegistrationFailedException
	@ExceptionHandler(RegistrationFailedException.class)
	public ResponseEntity<String> handleRegistrationFailedException(RegistrationFailedException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Handle CustomDatabaseException
	@ExceptionHandler(CustomDatabaseException.class)
	public ResponseEntity<ApiResponse> handleCustomDatabaseException(CustomDatabaseException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiResponse("Database error: " + ex.getMessage(), false));
	}

	// Handle CustomServiceException
	@ExceptionHandler(CustomServiceException.class)
	public ResponseEntity<ApiResponse> handleCustomServiceException(CustomServiceException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiResponse("Service error: " + ex.getMessage(), false));
	}

	// Handle other general exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception ex) {
		return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
