package com.at.t.eCommerce.service_impl.seller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import com.at.t.eCommerce.dto.seller.ProductRequestDTO;
import com.at.t.eCommerce.dto.seller.ProductResponseDTO;
import com.at.t.eCommerce.exception.CustomDatabaseException;
import com.at.t.eCommerce.exception.CustomServiceException;
import com.at.t.eCommerce.exception.NotFoundException;
import com.at.t.eCommerce.mapper.product.ProductMapper;
import com.at.t.eCommerce.model.ProductModel;
import com.at.t.eCommerce.repo.ProductModelRepo;
import com.at.t.eCommerce.service.seller.ProductService;
import com.at.t.eCommerce.util.GenerateUniqueIDForProduct;
import com.at.t.eCommerce.util.GetCategoryPrefix;
import com.at.t.eCommerce.util.UpdateHelper;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductModelRepo productRepo;

	@Autowired
	private GenerateUniqueIDForProduct uniqueIDForProduct;

	@Autowired
	private GetCategoryPrefix getCategoryPrefix;

	@Autowired
	private UpdateHelper updateHelper;

	@Override
	@Transactional
	public ProductResponseDTO addProduct(ProductRequestDTO productRequest) {

		try {

			ProductModel product = ProductMapper.mapToEntity(productRequest);
			String categoryPrefix = getCategoryPrefix.fetchCategoryPrefix(productRequest.getCategory());
			product.setUniqueID(uniqueIDForProduct.generateUniqueProductId(categoryPrefix));
			ProductModel savedProduct = productRepo.save(product);
			return ProductMapper.mapToDTO(savedProduct);

		} catch (DataIntegrityViolationException e) {

			throw new RuntimeException("Failed to generate a unique product ID after  attempts");
		}

	}

	@Override
	@Transactional
	public ProductResponseDTO updateProduct(String productID, ProductRequestDTO productRequest) {

		ProductModel existingProduct = productRepo.findByUniqueID(productID)
				.orElseThrow(() -> new NotFoundException("Product with uniqueID " + productID + " not found"));

		updateHelper.updateNonNullFields(productRequest, existingProduct);

		ProductModel updatedProduct = productRepo.save(existingProduct);

		return ProductMapper.mapToDTO(updatedProduct);
	}

	@Override
	public boolean deleteProduct(String productId) {
		try {
			boolean productExists = productRepo.existsByUniqueID(productId);
			if (!productExists) {
				// If the product doesn't exist, return false
				return false;
			}
			// Proceed with deletion if the product exists
			productRepo.deleteByUniqueID(productId);

			// Confirm that the product is no longer present
			return !productRepo.existsByUniqueID(productId);
		} catch (DataAccessException dae) {
			logger.error("Database error while deleting product with ID {}: {}", productId, dae.getMessage(), dae);
			throw new CustomDatabaseException("Failed to delete product due to database error", dae);

		} catch (Exception e) {
			logger.error("Unexpected error while deleting product with ID {}: {}", productId, e.getMessage(), e);
			throw new CustomServiceException("Failed to delete product due to unexpected error", e);
		}
	}

	@Override
	public List<ProductResponseDTO> getAllProducts(@PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {

		Page<ProductModel> allProductsPage = productRepo.getAllProducts(pageable);
		List<ProductModel> allProducts = allProductsPage.getContent();

		return ProductMapper.mapToDTOList(allProducts);
	}

	@Override
	public ProductResponseDTO getProductById(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
