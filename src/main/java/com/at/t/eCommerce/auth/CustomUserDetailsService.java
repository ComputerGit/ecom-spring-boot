package com.at.t.eCommerce.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.at.t.eCommerce.model.CoreUser;
import com.at.t.eCommerce.model.UserCredential;
import com.at.t.eCommerce.repo.CoreUserRepo;
import com.at.t.eCommerce.repo.UserCredentialRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final CoreUserRepo coreUserRepo;
	private final UserCredentialRepo userCredentialRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		CoreUser coreUser = coreUserRepo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

		UserCredential credential = userCredentialRepo.findByUser(coreUser)
				.orElseThrow(() -> new UsernameNotFoundException("Credentials not found for user: " + email));

		return org.springframework.security.core.userdetails.User.withUsername(coreUser.getEmail())
				.password(credential.getPasswordHash())
				.roles(coreUser.getRoles().stream().map(Enum::name).toArray(String[]::new)).build();
	}
}
