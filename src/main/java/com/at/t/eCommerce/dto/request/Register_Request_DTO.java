package com.at.t.eCommerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Register_Request_DTO {
	
    @NotBlank @Email @Size(max = 254)
	private String email;
    
	@Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$" , message = "Phone number is invalid")
	private String phone;
	
    @Size(min = 8 , max = 128)
	private String password;
    
    private String role;
}
