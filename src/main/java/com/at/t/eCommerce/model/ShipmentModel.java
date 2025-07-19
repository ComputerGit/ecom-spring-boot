package com.at.t.eCommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shipments", indexes = {
    @Index(name = "idx_order_id", columnList = "order_id"),
    @Index(name = "idx_tracking_number", columnList = "tracking_number")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @NotNull
    private OrderModel order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private ShipmentStatus shipmentStatus;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String trackingNumber;

    @Column(nullable = false, updatable = false)
    @NotNull
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShipmentItemModel> shipmentItems = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void addShipmentItem(ShipmentItemModel shipmentItem) {
        shipmentItems.add(shipmentItem);
        shipmentItem.setShipment(this);
    }

    public void removeShipmentItem(ShipmentItemModel shipmentItem) {
        shipmentItems.remove(shipmentItem);
        shipmentItem.setShipment(null);
    }

    public enum ShipmentStatus {
        PENDING, SHIPPED, DELIVERED, CANCELLED, RETURNED
    }
}
