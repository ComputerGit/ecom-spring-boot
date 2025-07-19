package com.at.t.eCommerce.util;

import lombok.Data;

@Data
public class ApiResponse {

	private String message;
	private Boolean success;

	// Constructor to initialize message and success fields
	public ApiResponse(String message, Boolean success) {
		this.message = message;
		this.success = success;
	}

	// Optional: You can also add a no-argument constructor if needed
	public ApiResponse() {
	}

}
