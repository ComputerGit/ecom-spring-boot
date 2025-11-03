package com.at.t.eCommerce.model;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_credentials")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder



//HAVE TO CHECK WHY WE HAVE TO USE EAGER FOR SOFTDELETE

public class UserCredential {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private CoreUser user;

	@Column(nullable = false)
	@NotBlank
	private String passwordHash;

	private Instant passwordChangedAt;

}
