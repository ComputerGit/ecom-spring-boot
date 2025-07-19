package com.at.t.eCommerce.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id" ,nullable = false)
	@NotNull
	private UserModel user;
	
    @NotBlank
    @Column(nullable = false)
    private String reason;

	private Integer quantity;
	
	@NotNull
	@Column(nullable = false)
	private String action;// Type of action performed (e.g., CREATE, UPDATE, DELETE)
	
	@Lob
	@NotNull
	private String description;
	
	@NotNull
	@Column(nullable = false ,updatable = false)
	private LocalDateTime createdAt;
	
	@Version
	private Integer version;
	
	
	@ElementCollection
	@CollectionTable(name = "audit_details", joinColumns = @JoinColumn(name = "audit_id"))
    @MapKeyColumn(name = "field_name")
    @Column(name = "field_value")
    private Map<String, String> auditDetails; // Optional: Key-value pairs for additional dynamic fields
	
	@PrePersist
	protected void onCreate() {
		
		createdAt = LocalDateTime.now();
	}
}
