package com.at.t.eCommerce.dto.seller;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long id;
    private String uniqueID;
    private String productName;
    private String description;
    private Double price;
    private String category;
    private String brand;
    private Long stockQuantity;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}