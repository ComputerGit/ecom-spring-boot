package com.at.t.eCommerce.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.at.t.eCommerce.auth.session.SessionService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JWTUtil jwtUtil;
	private final CustomUserDetailsService userDetailsService;
	private final SessionService sessionService;

	public JwtAuthenticationFilter(JWTUtil jwtUtil, CustomUserDetailsService userDetailsService,
			SessionService sessionService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
		this.sessionService = sessionService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ") || authorizationHeader.length() <= 7) {
			chain.doFilter(request, response);
			return;
		}

		String token = authorizationHeader.substring(7);

		try {

			Claims claim = jwtUtil.parseAndValidate(token);
			String jti = claim.getId();
			String username = claim.getSubject();

			if (!sessionService.sessionExists(jti)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			}
			chain.doFilter(request, response);

		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}

	}
}
