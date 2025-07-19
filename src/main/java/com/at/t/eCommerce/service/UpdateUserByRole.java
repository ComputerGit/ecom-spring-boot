package com.at.t.eCommerce.service;

import com.at.t.eCommerce.enums.Role;

public interface UpdateUserByRole {
	
	public boolean userRoleUpdate(String username , Role role);
	
}
