package com.at.t.eCommerce.service_impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.at.t.eCommerce.model.UserModel;
import com.at.t.eCommerce.repo.UserModelRepo;
import com.at.t.eCommerce.service.GetAllUsers;

@Service
public class Get_All_Users_Impl implements GetAllUsers {

	UserModelRepo userModelRepo;

	public Get_All_Users_Impl(UserModelRepo userModelRepo) {

		this.userModelRepo = userModelRepo;

	}

	@Override
	public List<UserModel> getAllUsers() {
		 
		return userModelRepo.findAll().stream().limit(10).toList();
	}



}
