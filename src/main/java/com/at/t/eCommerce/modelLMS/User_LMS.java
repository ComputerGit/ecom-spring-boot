package com.at.t.eCommerce.modelLMS;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import com.at.t.eCommerce.enums.Role;


@Entity
public class User_LMS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Email
	@NotBlank
	@Column(nullable = false , unique = true)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
}
