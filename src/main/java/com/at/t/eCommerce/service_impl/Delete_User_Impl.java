package com.at.t.eCommerce.service_impl;

import org.springframework.stereotype.Service;

import com.at.t.eCommerce.dto.User_Delete_DTO;
import com.at.t.eCommerce.model.UserModel;
import com.at.t.eCommerce.repo.UserModelRepo;
import com.at.t.eCommerce.service.user.DeleteUser;

@Service
public class Delete_User_Impl implements DeleteUser {

	private UserModelRepo userModelRepo;

	public Delete_User_Impl(UserModelRepo userModelRepo) {

		this.userModelRepo = userModelRepo;

	}

	@Override
	public boolean deleteUserByUsername(User_Delete_DTO by_Username_DTO) {
		boolean isDeleted = false;
		try {

			String username = by_Username_DTO.getUsername();
			userModelRepo.deleteByUserName(username);
			isDeleted = true;

			return isDeleted;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;

	}


}
