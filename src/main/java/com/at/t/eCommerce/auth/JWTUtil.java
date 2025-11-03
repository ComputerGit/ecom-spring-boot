package com.at.t.eCommerce.auth;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JWTUtil {

	private static final Logger log = LoggerFactory.getLogger(JWTUtil.class);

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.access-token-expiration}")
	private long tokenAccessExpiration;

	@Value("${jwt.refresh-token-expiration}")
	private long refreshTokenExpiration;

	@Value("${security.jwt.issuer:ecommerce-app}")
	private String issuer;

	@Value("${security.jwt.audience:ecommerce-clients}")
	private String audience;

	public String generateAccessToken(String username, String jti) {

		return Jwts.builder().setSubject(username).setId(jti).setIssuer(issuer).setAudience(audience)
				.setIssuedAt(new Date()).setExpiration(Date.from(Instant.now().plusMillis(tokenAccessExpiration)))
				.signWith(getKey(secretKey), SignatureAlgorithm.HS256).compact();
	}

	public String generateRefreshToken(String username, String jti) {

		return Jwts.builder().setSubject(username).setId(jti).setIssuer(issuer).setAudience(audience)
				.setIssuedAt(new Date()).setExpiration(Date.from(Instant.now().plusMillis(refreshTokenExpiration)))
				.signWith(getKey(secretKey), SignatureAlgorithm.HS256).compact();
	}

	public SecretKey getKey(String secretKey) {
		return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}

	public Claims parseAndValidate(String token) throws JwtException {

		SecretKey hmacShaKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

		return Jwts.parserBuilder().setSigningKey(hmacShaKey).requireIssuer(issuer).requireAudience(audience).build()
				.parseClaimsJws(token).getBody();

	}

	public String extractUsername(String token) {

		return parseAndValidate(token).getSubject();
	}

	public String extractJti(String token) {
		return parseAndValidate(token).getId();
	}

	public Boolean isTokenValid(String token, UserDetails userDetails) {

		Claims c = parseAndValidate(token);

		return c.getSubject().equals(userDetails.getUsername()) && c.getExpiration().after(new Date());
	}

//	@PostConstruct
//	public void init() {
//		if (secretKey == null || secretKey.trim().isEmpty()) {
//			log.atError().log("JWT secret key is missing during initialization!");
//			throw new IllegalArgumentException("JWT secret key must be provided");
//		}
//		log.atInfo().addKeyValue("tokenExpiration", tokenExpiration)
//				.addKeyValue("refreshTokenExpiration", refreshTokenExpiration).log("JWTUtil initialized successfully");
//	}

//	public String extractUsername(String token) {
//		try {
//			String username = extractAllClaims(token).getSubject();
//			log.atDebug().addKeyValue("username", username).log("Extracted username from token");
//			return username;
//		} catch (Exception e) {
//			log.atError().addKeyValue("reason", e.getMessage()).log("Failed to extract username from token");
//			throw e;
//		}
//	}

//	public boolean isTokenValid(String token, UserDetails userDetails) {
//		final String username = extractUsername(token);
//		boolean valid = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//
//		if (valid) {
//			log.atDebug().addKeyValue("username", username).log("Token is valid");
//		} else {
//			log.atWarn().addKeyValue("username", username).log("Invalid token detected");
//		}
//		return valid;
//	}

	private boolean isTokenExpired(String token) {
		boolean expired = extractExpiration(token).before(new Date());
		if (expired) {
			log.atWarn().log("JWT token has expired");
		}
		return expired;
	}

	public Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes())).build()
				.parseClaimsJws(token).getBody();
	}

}
