package com.at.t.eCommerce.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User_Register_DTO {

	private String userName;

	private String firstName;

	private String lastName;

	private String password;

	private String email;

	private String phone;

	private LocalDate dob;

}
