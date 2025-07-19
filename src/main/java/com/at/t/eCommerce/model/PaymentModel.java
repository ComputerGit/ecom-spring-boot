package com.at.t.eCommerce.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @NotNull
    private OrderModel order;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String transactionID;

    @NotBlank
    @Column(nullable = false)
    private String paymentMethod;

    @NotNull
    @Column(nullable = false)
    private Double amount;

    @NotBlank
    @Column(nullable = false)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private PaymentStatus paymentStatus;

    public enum PaymentStatus {
        PAID, PENDING, FAILED, RETRY, SUCCESS
    }

    @NotBlank
    @Column(nullable = false)
    private String paymentGateway;

    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(nullable = false)
    private LocalDate updatedAt;
}
