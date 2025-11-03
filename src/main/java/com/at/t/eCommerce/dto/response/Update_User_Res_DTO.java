package com.at.t.eCommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Update_User_Res_DTO {

	private String firstName;
	private String lastName;
	private String phone;
	private String address;	

}
