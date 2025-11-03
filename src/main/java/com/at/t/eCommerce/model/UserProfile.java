package com.at.t.eCommerce.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Table(name = "user_profiles")
@Data
@Entity
public class UserProfile {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String fullName;

		@PastOrPresent
		private LocalDate dob;

	    private String avatarUrl;

	    private String preferredLanguage;

	    private String defaultCurrency;
	    
	    @OneToOne
	    @JoinColumn(name = "user_id" , nullable = false , unique = true)
	    private CoreUser user;	
}
