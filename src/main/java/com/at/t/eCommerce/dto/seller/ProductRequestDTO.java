package com.at.t.eCommerce.dto.seller;

import lombok.Data;

@Data
public class ProductRequestDTO {
    private String productName;
    private String description;
    private Double price;
    private String category;
    private String brand;
    private Long stockQuantity;
}