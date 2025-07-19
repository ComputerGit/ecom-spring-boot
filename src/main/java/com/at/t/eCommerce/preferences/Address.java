package com.at.t.eCommerce.preferences;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	@NotBlank
	@Column(nullable = false)
	private String dno;
	
	@NotBlank
	@Column(nullable = false)
	private String street;
	
	@NotBlank
	@Column(nullable = false)
	private String city;
	
	@NotBlank
	@Column(nullable = false)
	private String state;
	
	@NotBlank
	@Column(nullable = false)
	private String country;
	
	@NotBlank
	@Column(nullable = false)
	private Long pincode;
 

}
