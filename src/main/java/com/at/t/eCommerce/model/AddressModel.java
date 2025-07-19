package com.at.t.eCommerce.model;

import java.time.LocalDate;

import com.at.t.eCommerce.preferences.Address;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	@Column(nullable = false)
	private Address address;
	
	@Column(nullable = false)
	private LocalDate createdAt;

	@Column(nullable = false)
	private LocalDate updatedAt;
}
