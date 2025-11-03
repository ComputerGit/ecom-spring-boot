package com.at.t.eCommerce.auth;

import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.at.t.eCommerce.auth.session.SessionService;
import com.at.t.eCommerce.dto.request.AuthenticationRequestDto;
import com.at.t.eCommerce.util.HashUtil;

import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class AuthenticationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

	private final AuthenticationManager authenticationManager;
	private final JWTUtil jwtUtil;
	private SessionService sessionService;

	public record AuthTokens(String accessToken, String refreshToken) {

	};

	public AuthTokens createAuthenticationToken(AuthenticationRequestDto requestDto) {

		try {

			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword()));

			UserDetails userDetails = (UserDetails) authenticate.getPrincipal();

			String jti = UUID.randomUUID().toString();

			String accessToken = jwtUtil.generateAccessToken(userDetails.getUsername(), jti);
			
			String refreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername(), jti);

			String refreshHashed = HashUtil.sha256B64(refreshToken);
			
			sessionService.createSession(jti, userDetails.getUsername(), refreshHashed,
					Map.of("Issued At" , String.valueOf(System.currentTimeMillis())));
			
			return new AuthTokens(accessToken, refreshToken);		

		} catch (Exception e) {

			LOGGER.error("Invalid credentials for {} ", requestDto.getEmail());
			throw new RuntimeException("Incorrect username or password");
			
		}
	}

}






















