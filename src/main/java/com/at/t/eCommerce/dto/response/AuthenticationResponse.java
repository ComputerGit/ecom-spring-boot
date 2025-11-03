package com.at.t.eCommerce.dto.response; // Adjust the package as per your project structure

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
	private String accessToken;

	private String refreshToken;

}
