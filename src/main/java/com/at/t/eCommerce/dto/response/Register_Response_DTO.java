package com.at.t.eCommerce.dto.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Register_Response_DTO {
	
	 private UUID id;
	 private String email;
	 private String accessToken;
	 private String refreshToken;
	 private String role;
	

}
