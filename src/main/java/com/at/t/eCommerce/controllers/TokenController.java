package com.at.t.eCommerce.controllers;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.at.t.eCommerce.auth.JWTUtil;
import com.at.t.eCommerce.auth.session.RefreshLua;
import com.at.t.eCommerce.auth.session.SessionService;
import com.at.t.eCommerce.dto.request.Refresh_Token_Request;
import com.at.t.eCommerce.dto.response.Token_Response_DTO;
import com.at.t.eCommerce.util.HashUtil;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("api/account")
@Slf4j
@RequiredArgsConstructor
public class TokenController {
	
	@Value("${jwt.refresh-token-expiration}")
	private long refreshTokenExpiration;

	@Value("${jwt.access-token-expiration}")
	private long tokenAccessExpiration;
	
	private final JWTUtil jwtUtil;
	private final RefreshLua refreshLua;
	private final SessionService sessionService;

	private final UserDetailsService userDetailsService;

	@PostMapping("/token/refresh")
	public ResponseEntity<?> refresh(@RequestBody Refresh_Token_Request request) {

		try {
			String refreshToken = request.getRefreshToken();
			Claims claim = jwtUtil.parseAndValidate(refreshToken);
			String jti = claim.getId();
			String username = claim.getSubject();

			if (!sessionService.sessionExists(jti)) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}

			String providedHash = HashUtil.sha256B64(refreshToken);

			String storedHash = sessionService.getStoredRefreshHash(jti);

			if (storedHash == null || !storedHash.equals(providedHash)) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Hash not Matching");
			}

			String newAccessToken = jwtUtil.generateAccessToken(username, jti);
			String newRefreshToken = jwtUtil.generateRefreshToken(username, jti);

			String newHash = HashUtil.sha256B64(newRefreshToken);

			String refreshKey = sessionService.refreshKeyFor(jti);
			String sessionKey = sessionService.sessionKeyFor(jti);
			Duration ttl = Duration.ofMillis(refreshTokenExpiration);

			long result = refreshLua.rotate(refreshKey, sessionKey, providedHash, newHash, ttl);

			if (result != 1) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}

			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			String role = "USER";

			if (userDetails != null && userDetails.getAuthorities() != null) {
				role = userDetails.getAuthorities().iterator().next().getAuthority();
			}

			Token_Response_DTO response = Token_Response_DTO.builder().accessToken(newAccessToken)
					.refreshToken(newRefreshToken).tokenType("Bearer").expiresIn(tokenAccessExpiration / 1000)
					.email(username).role(role).build();

			return ResponseEntity.ok(response);

		} catch (JwtException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
