package com.at.t.eCommerce.auth;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.at.t.eCommerce.model.UserModel;
import com.at.t.eCommerce.repo.UserModelRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserModelRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the repository using the username
    	UserModel user = repo.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
                
        // Return a Spring Security User object
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), 
                user.getPassword(),
                new ArrayList<>() // Authorities (roles) can be added here if needed
        );
    }
}
