package com.at.t.eCommerce.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "wishlist_items",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"wishlist_id", "product_id"})}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishListItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wishlist_id", nullable = false)
    @NotNull
    private WishListModel wishlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @NotNull
    private ProductModel product;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime addedAt;
    
    @Column(nullable = false)
    @NotNull
    private LocalDateTime removedAt;
}
