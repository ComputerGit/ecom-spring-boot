package com.at.t.eCommerce.model;

import java.time.LocalDate;
import java.util.List;

import com.at.t.eCommerce.enums.Role;
import com.at.t.eCommerce.preferences.Address;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String userName;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;
	
	@NotBlank
	@Size(min = 8)
	private String password;

	@Email
	@NotBlank
	@Column(nullable = false , unique = true)
	private String email;

	@Column(nullable = false,unique = true)
	@Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$" , message = "Phone number is invalid")
	private String phone;

	@NotNull
	@Past
	private LocalDate dob;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "dno", column = @Column(name = "door_no")),
			@AttributeOverride(name = "street", column = @Column(name = "resident_street")),
			@AttributeOverride(name = "city", column = @Column(name = "resident_city")),
			@AttributeOverride(name = "state", column = @Column(name = "resident_state")),
			@AttributeOverride(name = "country", column = @Column(name = "resident_country")),
			@AttributeOverride(name = "pincode", column = @Column(name = "resident_pincode")) })
	private Address address;
	
	@OneToOne(mappedBy = "user" , cascade = CascadeType.ALL  , fetch = FetchType.LAZY)
	private CartModel activeCart;

	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	private List<AuditModel> auditLogs;
	 
	@OneToMany(mappedBy = "createdBy" , fetch = FetchType.LAZY , cascade = CascadeType.ALL ) 
	private List<DiscountModel> createdDiscounts ;
	
	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	private List<NotificationModel> notifications;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OrderModel> orders;
	
	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ReviewModel> reviews;
	
	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<WishListModel> wishlist;
	

}
