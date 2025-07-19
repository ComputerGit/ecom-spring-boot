package com.at.t.eCommerce.model;

import java.time.LocalDateTime;
import java.util.List;

import com.at.t.eCommerce.config.DiscountConfig;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "discounts",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})},
    indexes = {@Index(name = "idx_start_date_end_date", columnList = "start_date, end_date")}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String code;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @Min(0)
    @Max(100)
    @Column(nullable = false)
    private Double discountPercentage;

    @FutureOrPresent
    @Column(nullable = false)
    private LocalDateTime startDate;

    @Future
    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false, updatable = false)
    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "discountProducts",
        joinColumns = @JoinColumn(name = "discount_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductModel> applicableProducts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    @NotNull
    private UserModel createdBy;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();	
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @NotNull
    @Column(nullable = false)
    private String discountType;

    public void setDiscountType(String discountType, DiscountConfig discountConfig) {
        if(discountConfig.getTypes().contains(discountType)) {
            this.discountType = discountType;
        } else {
            throw new IllegalArgumentException("Invalid Discount Type: " + discountType);
        }
    }
}
