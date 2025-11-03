package com.at.t.eCommerce.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.at.t.eCommerce.model.CoreUser;
import com.at.t.eCommerce.model.UserCredential;

@Repository
public interface UserCredentialRepo extends JpaRepository<UserCredential, UUID> {

	    Optional<UserCredential> findByUser(CoreUser user);
	
}
