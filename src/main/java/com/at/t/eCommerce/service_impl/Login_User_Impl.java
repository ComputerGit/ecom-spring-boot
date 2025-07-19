package com.at.t.eCommerce.service_impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.at.t.eCommerce.dto.User_Login_DTO;
import com.at.t.eCommerce.mapper.User_Login_Mapper;
import com.at.t.eCommerce.model.UserModel;
import com.at.t.eCommerce.repo.UserModelRepo;
import com.at.t.eCommerce.service.LoginUser;

@Service
public class Login_User_Impl implements LoginUser {

    private final UserModelRepo userModelRepo;
    private final PasswordEncoder passwordEncoder;

    public Login_User_Impl(UserModelRepo userModelRepo, PasswordEncoder passwordEncoder) {
        this.userModelRepo = userModelRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User_Login_DTO user_Login_DTO(User_Login_DTO user_Login_DTO) {

        Optional<UserModel> userOptional = userModelRepo.findByUserName(user_Login_DTO.getUsername());

        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();
            if (passwordEncoder.matches(user_Login_DTO.getPassword(), user.getPassword())) {
                return User_Login_Mapper.mapToUserLoginDTO(user);
            }
        }

        return null; // Return null if the user is not found or password does not match
    }
}
