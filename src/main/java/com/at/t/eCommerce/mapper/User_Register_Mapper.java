package com.at.t.eCommerce.mapper;

import com.at.t.eCommerce.dto.User_Register_DTO;
import com.at.t.eCommerce.model.UserModel;

public class User_Register_Mapper {

	public static UserModel mapToUserRegister(User_Register_DTO user_Register_DTO) {
	    return new UserModel(
	        null,
	        user_Register_DTO.getUserName(),
	        user_Register_DTO.getFirstName(),
	        user_Register_DTO.getLastName(),
	        user_Register_DTO.getPassword(),
	        user_Register_DTO.getEmail(),
	        user_Register_DTO.getPhone(), 
	        user_Register_DTO.getDob(),
	        null,
	        null, null, null, null, null, null, null, null
	    );
	}

	public static User_Register_DTO mapToUserRegisterDTO(UserModel userModel) {
	    return new User_Register_DTO(
	        userModel.getUserName(),
	        userModel.getFirstName(),
	        userModel.getLastName(),
	        null, // password is not included in the DTO
	        userModel.getEmail(),
	        userModel.getPhone(),
	        userModel.getDob()
	    );

}
}
