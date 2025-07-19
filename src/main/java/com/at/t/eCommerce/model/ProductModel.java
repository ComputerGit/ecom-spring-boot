package com.at.t.eCommerce.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "products", indexes = {
		@Index(name = "idx_product_name", columnList = "productName") })

public class ProductModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
    @Column(unique = true , nullable = false)
	private String uniqueID;

	@NotBlank
	@Column(nullable = false)
	private String productName;

	
	@Basic(fetch = FetchType.EAGER)
	@NotBlank
	@Column(nullable = false)
	private String description;

	@NotNull
	@Column(nullable = false)
	private Double price;

	@NotBlank
	@Column(nullable = false)
	private String category;

	@NotBlank
	@Column(nullable = false)
	private String brand;

	@NotNull
	@Column(nullable = false)
	private Long stockQuantity;

	@NotNull
	@Column(nullable = false)
	private LocalDate createdAt;

	@NotNull
	@Column(nullable = false)
	private LocalDate updatedAt;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CartItemModel> cartItems;

	@ManyToMany(mappedBy = "applicableProducts", fetch = FetchType.LAZY)
	private List<DiscountModel> discountProducts;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<InventoryModel> inventoryStock;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OrderItemModel> orderItems;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ReviewModel> reviewProduct;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<WishListItemModel> wishlistItems;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductImageModel> productImages;

}
