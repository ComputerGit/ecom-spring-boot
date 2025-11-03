package com.at.t.eCommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.at.t.eCommerce.dto.request.Update_User_Req_DTO;
import com.at.t.eCommerce.dto.response.Register_Response_DTO;
import com.at.t.eCommerce.dto.response.Update_User_Res_DTO;
import com.at.t.eCommerce.model.CoreUser;
import com.at.t.eCommerce.model.UserProfile;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	Register_Response_DTO toRegisterResponse(CoreUser user);
	
	Update_User_Res_DTO update_User_Res_DTO(CoreUser user);
	
    void updateCoreUserFromDto(Update_User_Req_DTO dto , @MappingTarget  CoreUser user);
    
    void updateUserDetailsFromDto(Update_User_Req_DTO dto , @MappingTarget UserProfile userProfile);
	
}
