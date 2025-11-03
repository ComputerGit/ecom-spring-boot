package com.at.t.eCommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token_Response_DTO {

	private String accessToken;
	private String refreshToken;
	private String tokenType; // usually "Bearer"
	private Long expiresIn; // optional - expiry time in seconds
	private String email; // optional - info about the user
	private String role; // optional - user's role

}
