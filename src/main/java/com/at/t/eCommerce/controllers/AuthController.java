package com.at.t.eCommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.t.eCommerce.auth.CustomUserDetailsService;
import com.at.t.eCommerce.auth.JWTUtil;
import com.at.t.eCommerce.dto.AuthenticationRequest;
import com.at.t.eCommerce.dto.AuthenticationResponse;
import com.at.t.eCommerce.dto.User_Delete_DTO;
import com.at.t.eCommerce.dto.User_Login_DTO;
import com.at.t.eCommerce.dto.User_Register_DTO;
import com.at.t.eCommerce.enums.Role;
import com.at.t.eCommerce.exception.UserAlreadyExistsException;
import com.at.t.eCommerce.model.UserModel;
import com.at.t.eCommerce.repo.UserModelRepo;
import com.at.t.eCommerce.service.GetAllUsers;
import com.at.t.eCommerce.service.LoginUser;
import com.at.t.eCommerce.service.RegisterUser;
import com.at.t.eCommerce.service.UpdateUserByRole;
import com.at.t.eCommerce.service.user.DeleteUser;
import com.at.t.eCommerce.service.user.UpdateUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/account")
@Slf4j
public class AuthController {

	private final RegisterUser registerUser;
	private final LoginUser loginUser;
	private final DeleteUser deleteUser;
	private final GetAllUsers allUsers;
	private final UpdateUser updateUser;
	private final AuthenticationManager authenticationManager;
	private final CustomUserDetailsService userDetailsService;
	private final JWTUtil jwtUtil;

	@Autowired
	private UpdateUserByRole updateUserByRole;

	public AuthController(RegisterUser registerUser, LoginUser loginUser, DeleteUser deleteUser, GetAllUsers allUsers,
			UpdateUser updateUser, AuthenticationManager authenticationManager,
			CustomUserDetailsService userDetailsService, JWTUtil jwtUtil) {
		this.registerUser = registerUser;
		this.loginUser = loginUser;
		this.deleteUser = deleteUser;
		this.allUsers = allUsers;
		this.updateUser = updateUser;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> registerUsers(@RequestBody List<User_Register_DTO> users) {
		log.info("Registering multiple users.");

		List<User_Register_DTO> registeredUsers = new ArrayList<>();
		List<String> conflicts = new ArrayList<>();
		boolean hasErrors = false;

		for (User_Register_DTO user : users) {
			try {
				User_Register_DTO registeredUser = registerUser.registerUser(user);
				registeredUsers.add(registeredUser);
			} catch (UserAlreadyExistsException e) {
				log.error("Conflict for user {}: {}", user.getUserName(), e.getMessage());
				conflicts.add(e.getMessage());
				hasErrors = true;
			} catch (Exception e) {
				log.error("An unexpected error occurred for user {}: {}", user.getUserName(), e.getMessage());
				conflicts.add("An unexpected error occurred for user " + user.getUserName());
				hasErrors = true;
			}
		}

		Map<String, Object> response = new HashMap<>();
		response.put("registeredUsers", registeredUsers);
		response.put("conflicts", conflicts);

		return hasErrors ? new ResponseEntity<>(response, HttpStatus.CONFLICT)
				: new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/user/{username}/role")
	public ResponseEntity<?> updateUserRole(@PathVariable String username, @RequestParam Role role) {

		boolean userRoleUpdate = updateUserByRole.userRoleUpdate(username, role);

		if (userRoleUpdate) {
			return ResponseEntity.status(HttpStatus.CREATED).body("User Role Changed");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or role update failed.");
		}

	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
		try {
			// Attempt to authenticate the user with the provided credentials
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			// Return a 401 Unauthorized response if credentials are invalid
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
		}

		// Load user details from the database
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		// Generate JWT token for the authenticated user
		final String jwt = jwtUtil.generateToken(userDetails.getUsername());

		// Return the token in the response
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@GetMapping("/login")
	public ResponseEntity<User_Login_DTO> loginUser(@RequestBody User_Login_DTO userLogin) {
		try {
			User_Login_DTO user = loginUser.user_Login_DTO(userLogin);
			if (user != null) {
				return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/home").build();
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			log.error("Login failed", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get-all")
	public ResponseEntity<List<UserModel>> getAllUsers() {
		List<UserModel> users = allUsers.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("/update-dob")
	public ResponseEntity<Boolean> updateDOB(@RequestParam String dob, @RequestParam String username) {
		boolean updateUserDOB = updateUser.updateUserDOB(dob, username);
		return updateUserDOB ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
	}

	@DeleteMapping("/delete/by-username")
	public ResponseEntity<Boolean> deleteUserByName(@RequestBody User_Delete_DTO userName) {
		try {
			deleteUser.deleteUserByUsername(userName);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}