
package com.at.t.eCommerce.service_impl;

import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.at.t.eCommerce.auth.JWTUtil;
import com.at.t.eCommerce.auth.session.SessionService;
import com.at.t.eCommerce.dto.request.Register_Request_DTO;
import com.at.t.eCommerce.dto.response.Register_Response_DTO;
import com.at.t.eCommerce.enums.Role;
import com.at.t.eCommerce.exception.UserAlreadyExistsException;
import com.at.t.eCommerce.factory.CoreUserFactory;
import com.at.t.eCommerce.model.CoreUser;
import com.at.t.eCommerce.repo.CoreUserRepo;
import com.at.t.eCommerce.service.RegisterUser;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class Register_User_Impl implements RegisterUser {

	private static final Logger LOGGER = LoggerFactory.getLogger(Register_User_Impl.class);

	private final CoreUserRepo coreUserRepo;
	private final PasswordEncoder passwordEncoder;
	private final JWTUtil jwtUtil;
	private final SessionService sessionService;

	@Override
	public Register_Response_DTO registerUser(Register_Request_DTO request) {

		String email = request.getEmail();
		String rawPassword = request.getPassword();
		String phone = request.getPhone();

		if (email.isBlank() || rawPassword.isBlank()) {
			throw new IllegalArgumentException("EMAIL AND PASSWORD IS INCORRECT");
		}

		if (coreUserRepo.existsByEmail(email)) {
			throw new UserAlreadyExistsException("User already Exists uh ah");
		}

		String encodedPassword = passwordEncoder.encode(rawPassword);
		Role role = resolveRoleOrDefault(request.getRole());

		CoreUser user = switch (role) {
		case SELLER -> CoreUserFactory.of(email, phone, encodedPassword, Set.of(Role.SELLER));
		case BUYER -> CoreUserFactory.of(email, phone, encodedPassword, Set.of(Role.BUYER));
		default -> throw new IllegalArgumentException("Unexcepted Value : " + role);
		};
        

		String jti = UUID.randomUUID().toString();
		String accessToken = jwtUtil.generateAccessToken(request.getEmail(), jti);
		String refreshToken = jwtUtil.generateRefreshToken(request.getEmail(), jti);
		
		sessionService.createSession(jti, email, refreshToken, null);
		
		CoreUser savedUser = coreUserRepo.save(user);

		return new Register_Response_DTO(savedUser.getId(), savedUser.getEmail(), accessToken, refreshToken,
				role.name());
	}

	public Role resolveRoleOrDefault(String role) {

		if (role == null || role.isBlank())
			return Role.BUYER;

		try {
			return Role.valueOf(role.trim().toUpperCase());
		} catch (IllegalArgumentException e) {

			return Role.BUYER;
		}
	}

}
