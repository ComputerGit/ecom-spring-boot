package com.at.t.eCommerce.mapper;

import java.util.Optional;

import com.at.t.eCommerce.dto.User_Login_DTO;
import com.at.t.eCommerce.model.UserModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class User_Login_Mapper {
	
	public static UserModel mapToUser( User_Login_DTO dto) {
	
		return new UserModel(null, dto.getUsername(),dto.getPassword(), null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}
	
	public static User_Login_DTO mapToUserLoginDTO(UserModel user) {
		
		
		return new User_Login_DTO(user.getUserName() , user.getPassword());
	}
 
}
