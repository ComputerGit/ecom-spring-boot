package com.at.t.eCommerce.service_impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.at.t.eCommerce.enums.Role;
import com.at.t.eCommerce.model.UserModel;
import com.at.t.eCommerce.repo.UserModelRepo;
import com.at.t.eCommerce.service.UpdateUserByRole;

@Service
public class Update_User_By_Role_Impl implements UpdateUserByRole {

	@Autowired
	UserModelRepo repo;

	@Override
	public boolean userRoleUpdate(String username, Role role) {

		try {

			Optional<UserModel> userOptional = repo.findByUserName(username);

			if (userOptional.isPresent()) {

				UserModel user = userOptional.get();

				user.setRole(role);

				repo.save(user);

				return true;

			} else {

				throw new UsernameNotFoundException("User not found with username: " + username);
			}

		} catch (UsernameNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("An error occurred while updating the role: " + e.getMessage());

		}

		return false;
	}

}
