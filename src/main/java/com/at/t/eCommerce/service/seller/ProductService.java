package com.at.t.eCommerce.service.seller;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.at.t.eCommerce.dto.seller.ProductRequestDTO;
import com.at.t.eCommerce.dto.seller.ProductResponseDTO;


public interface ProductService {
    ProductResponseDTO addProduct(ProductRequestDTO productRequest);
    ProductResponseDTO updateProduct(String productId, ProductRequestDTO productRequest);
    boolean deleteProduct(String productId);
    List<ProductResponseDTO> getAllProducts(Pageable pageable);
    ProductResponseDTO getProductById(String productId);
}