package com.at.t.eCommerce.service_impl;

import org.springframework.stereotype.Service;

import com.at.t.eCommerce.dto.request.Update_User_Req_DTO;
import com.at.t.eCommerce.dto.response.Update_User_Res_DTO;
import com.at.t.eCommerce.exception.NotFoundException;
import com.at.t.eCommerce.mapper.UserMapper;
import com.at.t.eCommerce.model.CoreUser;
import com.at.t.eCommerce.repo.CoreUserRepo;
import com.at.t.eCommerce.service.UpdateUser;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Update_User_Impl implements UpdateUser {

	private CoreUserRepo userRepo;
	private UserMapper userMapper;

	@Override
	@Transactional
	public Update_User_Res_DTO updateUser(String email, Update_User_Req_DTO requestDTO) {

		try {
			CoreUser user = userRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("the User not found"));

			userMapper.updateCoreUserFromDto(requestDTO, user);

			userMapper.updateUserDetailsFromDto(requestDTO, user.getProfile());

			CoreUser updated = userRepo.save(user);

			return userMapper.update_User_Res_DTO(updated);

		} catch (Exception e) {

			throw new RuntimeException("Error at Updating", e);
		}

	}

}
