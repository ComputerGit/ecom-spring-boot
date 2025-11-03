package com.at.t.eCommerce.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Update_User_Req_DTO {

	private String firstName;
	private String lastName;
	private String phone;
	private String address;

}
