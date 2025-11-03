package com.at.t.eCommerce.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.at.t.eCommerce.dto.request.AuthenticationRequestDto;
import com.at.t.eCommerce.dto.request.Register_Request_DTO;
import com.at.t.eCommerce.dto.request.Update_User_Req_DTO;
import com.at.t.eCommerce.dto.response.AuthenticationResponse;
import com.at.t.eCommerce.dto.response.Register_Response_DTO;
import com.at.t.eCommerce.dto.response.Update_User_Res_DTO;
import com.at.t.eCommerce.service.LoginUser;
import com.at.t.eCommerce.service.RegisterUser;
import com.at.t.eCommerce.service.UpdateUser;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/account")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	private final RegisterUser registerUser;
	private final LoginUser loginUser;
	private final UpdateUser updateUser;
//	private final HashUtil hashUtil;

	public record RefreshRequest(String accessToken, String refreshToken) {
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody Register_Request_DTO request) {
		try {

			Register_Response_DTO response = registerUser.registerUser(request);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		} catch (Exception e) {

			log.error("Error Registering User", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequestDto requestDto) {
		LOGGER.info("Login attempt for user: {}", requestDto.getEmail());
		try {
			ResponseEntity<AuthenticationResponse> response = loginUser.requestDto(requestDto);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@PutMapping("/update")
	public ResponseEntity<?> updateUserProfile(@PathVariable
			@Valid @RequestBody Update_User_Req_DTO request) {
		try {

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String email = null; 
			
			if(authentication != null) {
				email = authentication.getName();
			}else {
				return ResponseEntity.status(HttpStatus.LOCKED).body("YOU MUST LOG IN TO GET ACCESS TO UPDATE");
			}
			
			log.info(email + " THIS THE AUTHORIZER ......!");	

			Update_User_Res_DTO response = updateUser.updateUser(email, request);
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}
}














