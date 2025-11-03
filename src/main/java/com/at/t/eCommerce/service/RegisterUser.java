package com.at.t.eCommerce.service;


import com.at.t.eCommerce.dto.request.Register_Request_DTO;
import com.at.t.eCommerce.dto.response.Register_Response_DTO;


public interface RegisterUser {  
	
	public Register_Response_DTO registerUser(Register_Request_DTO user);
	

}
