package com.at.t.eCommerce.mapper.product;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.at.t.eCommerce.dto.seller.ProductRequestDTO;
import com.at.t.eCommerce.dto.seller.ProductResponseDTO;
import com.at.t.eCommerce.model.ProductModel;

public class ProductMapper {

	public static ProductResponseDTO mapToDTO(ProductModel product) {
		ProductResponseDTO dto = new ProductResponseDTO();
		dto.setId(product.getId());
		dto.setUniqueID(product.getUniqueID());
		dto.setProductName(product.getProductName());
		dto.setDescription(product.getDescription());
		dto.setPrice(product.getPrice());
		dto.setCategory(product.getCategory());
		dto.setBrand(product.getBrand());
		dto.setStockQuantity(product.getStockQuantity());
		dto.setCreatedAt(product.getCreatedAt());
		dto.setUpdatedAt(product.getUpdatedAt());
		return dto;
	}

	// Map ProductRequestDTO to ProductModel (optional, if you need reverse mapping)
	public static ProductModel mapToEntity(ProductRequestDTO productRequest) {
		ProductModel product = new ProductModel();
		product.setProductName(productRequest.getProductName());
		product.setDescription(productRequest.getDescription());
		product.setPrice(productRequest.getPrice());
		product.setCategory(productRequest.getCategory());
		product.setBrand(productRequest.getBrand());
		product.setStockQuantity(productRequest.getStockQuantity());
		product.setCreatedAt(LocalDate.now());
		product.setUpdatedAt(LocalDate.now());
		return product;
	}
	
	
	public static List<ProductResponseDTO> mapToDTOList (List<ProductModel> products){
		
		return products.stream().map(ProductMapper::mapToDTO).collect(Collectors.toList());
	}
}
