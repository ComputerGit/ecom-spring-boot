package com.at.t.eCommerce.factory;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.at.t.eCommerce.enums.AccountStatus;
import com.at.t.eCommerce.enums.Role;
import com.at.t.eCommerce.model.CoreUser;
import com.at.t.eCommerce.model.UserCredential;

@Component
public final class CoreUserFactory {

	private CoreUserFactory() {
	};

	public static CoreUser of(String email, String phone, String password, Set<Role> roles) {
		
		UserCredential credential = UserCredential.builder().passwordHash(password).build();

		CoreUser user = CoreUser.builder().email(email).phone(phone)
				.roles(roles == null  || roles.isEmpty() ? Set.of(Role.BUYER) : roles).status(AccountStatus.ACTIVE)
				.build();
		
		credential.setUser(user);
		user.setUserCredential(credential);
			
		return user;
	}
}
