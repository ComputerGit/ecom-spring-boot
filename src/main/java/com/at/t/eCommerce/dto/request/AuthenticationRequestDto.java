package com.at.t.eCommerce.dto.request; // Adjust the package as per your project structure

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDto {
	
	@NotBlank(message =  "Username cannot be Empty")
    private String email;
	@Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

   
}
