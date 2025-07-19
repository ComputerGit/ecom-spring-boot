package com.at.t.eCommerce.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.at.t.eCommerce.model.ProductModel;

@Repository
public interface ProductModelRepo extends JpaRepository<ProductModel, Long> {

	List<ProductModel> findByProductName(String productName);

	List<ProductModel> findByProductNameContainingIgnoreCase(String productName);

	@Query("SELECT p FROM ProductModel p WHERE p.category = :category")
	List<ProductModel> findByCategory(String category);

	List<ProductModel> findByOrderByPriceAsc();

	List<ProductModel> findByOrderByPriceDesc();

	boolean existsByUniqueID(String str);

	Optional<ProductModel> findByUniqueID(String uniqueID);

	Optional<Boolean> deleteByUniqueID(String uniqueID);

	@Query(value = "SELECT * FROM products", nativeQuery = true)
	
	
	Page<ProductModel> getAllProducts(Pageable pageable);

}
