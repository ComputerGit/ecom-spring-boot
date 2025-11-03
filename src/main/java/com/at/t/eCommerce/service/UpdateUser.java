package com.at.t.eCommerce.service;



import com.at.t.eCommerce.dto.request.Update_User_Req_DTO;
import com.at.t.eCommerce.dto.response.Update_User_Res_DTO;


public interface UpdateUser {
	
	Update_User_Res_DTO updateUser( String email, Update_User_Req_DTO requestDTO);
	
}
