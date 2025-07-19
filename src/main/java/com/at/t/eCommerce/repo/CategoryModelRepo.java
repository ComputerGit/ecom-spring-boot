package com.at.t.eCommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.at.t.eCommerce.model.CategoryModel;

@Repository
public interface CategoryModelRepo extends JpaRepository<CategoryModel, Long> {

}
