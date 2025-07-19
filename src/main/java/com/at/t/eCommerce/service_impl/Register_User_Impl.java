package com.at.t.eCommerce.service_impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.at.t.eCommerce.dto.User_Register_DTO;
import com.at.t.eCommerce.exception.RegistrationFailedException;
import com.at.t.eCommerce.exception.UserAlreadyExistsException;
import com.at.t.eCommerce.mapper.UserRegisterMapper;
import com.at.t.eCommerce.model.UserModel;
import com.at.t.eCommerce.repo.UserModelRepo;
import com.at.t.eCommerce.service.RegisterUser;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Register_User_Impl implements RegisterUser {

    private final UserModelRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public Register_User_Impl(UserModelRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User_Register_DTO registerUser(User_Register_DTO user) {
        // Check if the user already exists by email or phone
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists.");
        } else if (userRepo.existsByPhone(user.getPhone())) {
            throw new UserAlreadyExistsException("User with phone " + user.getPhone() + " already exists.");
        }

        try {
            // Map the DTO to the UserModel entity
            UserModel mapToUserRegister = UserRegisterMapper.INSTANCE.toEntity(user);
            // Encode the password before saving
            mapToUserRegister.setPassword(passwordEncoder.encode(user.getPassword()));
            // Save the user to the database
            UserModel savedUser = userRepo.save(mapToUserRegister);

            log.info("Successfully registered user: {}", user.getEmail());
            // Map back to DTO and return
            return UserRegisterMapper.INSTANCE.toDTO(savedUser);
        } catch (Exception e) {
            log.error("Registration failed for user {}: {}", user.getEmail(), e.getMessage(), e);
            throw new RegistrationFailedException("Registration failed due to an internal error.");
        }
    }
}
