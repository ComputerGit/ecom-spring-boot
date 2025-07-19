package com.at.t.eCommerce.service.user;

import com.at.t.eCommerce.dto.User_Delete_DTO;

public interface DeleteUser {
	
   public boolean deleteUserByUsername(User_Delete_DTO by_Username_DTO);
   

}
