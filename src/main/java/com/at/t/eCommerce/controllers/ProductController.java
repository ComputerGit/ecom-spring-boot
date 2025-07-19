package com.at.t.eCommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.at.t.eCommerce.dto.seller.ProductRequestDTO;
import com.at.t.eCommerce.dto.seller.ProductResponseDTO;
import com.at.t.eCommerce.exception.CustomDatabaseException;
import com.at.t.eCommerce.exception.CustomServiceException;
import com.at.t.eCommerce.service.seller.ProductService;
import com.at.t.eCommerce.util.ApiResponse;
import com.nimbusds.common.contenttype.ContentType.Parameter;

@RestController
@RequestMapping("/api/seller")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<List<ProductResponseDTO>> getAllProducts(Pageable page) {

		List<ProductResponseDTO> allProducts = productService.getAllProducts(page);

		return ResponseEntity.status(HttpStatus.OK).body(allProducts);

	}

	@PostMapping("/product")
	public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO requestDTO) {

		try {

			ProductResponseDTO product = productService.addProduct(requestDTO);

			return ResponseEntity.status(201).body(product);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@PutMapping("/{productID}")
	public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable String productID,
			@RequestBody ProductRequestDTO requestDTO) {

		return ResponseEntity.ok(productService.updateProduct(productID, requestDTO));
	}

	@DeleteMapping("/{productID}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable String productID) {

		try {

			boolean isDeleted = productService.deleteProduct(productID);

			if (isDeleted) {
				return ResponseEntity.ok(new ApiResponse("Product is Deleted SuccessFully", true));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ApiResponse("Product Not Found to Delete", false));
			}

		} catch (CustomDatabaseException e) {
			// Catches database-specific errors
			return ResponseEntity.internalServerError()
					.body(new ApiResponse("Database error: " + e.getMessage(), false));
		} catch (CustomServiceException e) {
			// Return a 500 status with a generic service error message
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse("Service error: " + e.getMessage(), false));
		} catch (Exception e) {
			// Catch any other unexpected error
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse("An unexpected error occurred: " + e.getMessage(), false));
		}

	}

}
