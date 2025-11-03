package com.at.t.eCommerce.service_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.at.t.eCommerce.auth.AuthenticationService;
import com.at.t.eCommerce.auth.AuthenticationService.AuthTokens;
import com.at.t.eCommerce.dto.request.AuthenticationRequestDto;
import com.at.t.eCommerce.dto.response.AuthenticationResponse;
import com.at.t.eCommerce.service.LoginUser;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class Login_User_Impl implements LoginUser {

	@Autowired
	public final AuthenticationService authenticationService;

	@Override
	public ResponseEntity<AuthenticationResponse> requestDto(AuthenticationRequestDto requestDto) {

		try {

			AuthTokens tokens = authenticationService.createAuthenticationToken(requestDto);

			AuthenticationResponse response = new AuthenticationResponse(tokens.accessToken() , tokens.refreshToken());

			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

	}

}
