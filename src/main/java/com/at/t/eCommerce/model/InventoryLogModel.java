package com.at.t.eCommerce.model;

import java.time.LocalDateTime;

import com.at.t.eCommerce.config.InventoryLogModelConfig;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventory_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryLogModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inventory_id", nullable = false)
	@NotNull
	private InventoryModel inventory;

	@Column(nullable = false)
	@NotNull
	private LocalDateTime timestamp;

	@NotNull
	private Integer quantity;

	@NotBlank
	@Column(nullable = false)
	private String reason; // Explanation for the change, e.g., "New stock arrived" or "Sold 5 units"

	@PrePersist
	protected void onCreate() {
		timestamp = LocalDateTime.now();
	}

	@Column(nullable = false)
	@NotNull
	private String changeType; // Using enum for change type

	public void setChangeType(InventoryLogModelConfig logModelConfig) {

		if (logModelConfig.getTypes().contains(changeType)) {

			this.changeType = changeType;
		}

		else {

			throw new IllegalArgumentException("Invalid ChangeType :" + changeType);
		}
	}

}
