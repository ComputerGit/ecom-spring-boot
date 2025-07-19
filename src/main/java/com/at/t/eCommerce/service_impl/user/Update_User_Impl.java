package com.at.t.eCommerce.service_impl.user;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.t.eCommerce.model.UserModel;
import com.at.t.eCommerce.repo.UserModelRepo;
import com.at.t.eCommerce.service.user.UpdateUser;

@Service
public class Update_User_Impl implements UpdateUser {

	@Autowired
	UserModelRepo repo;

	@Override
	public boolean updateUserDOB(String dob, String username) {

		Optional<UserModel> byUserName = repo.findByUserName(username);

		if (byUserName != null) {
			LocalDate date = LocalDate.parse(dob);
			int updateByDOB = repo.updateUserByDOB(date, username);

			return updateByDOB > 0;

		}

		return false;
	}

}
