package com.at.t.eCommerce.service;

import org.springframework.http.ResponseEntity;

import com.at.t.eCommerce.dto.request.AuthenticationRequestDto;
import com.at.t.eCommerce.dto.response.AuthenticationResponse;

public interface LoginUser  {
		
	public ResponseEntity<AuthenticationResponse> requestDto(AuthenticationRequestDto requestDto);

}
