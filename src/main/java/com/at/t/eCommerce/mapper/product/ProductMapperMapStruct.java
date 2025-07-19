package com.at.t.eCommerce.mapper.product;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.at.t.eCommerce.dto.seller.ProductRequestDTO;
import com.at.t.eCommerce.model.ProductModel;

@Mapper(componentModel = "spring")
public interface ProductMapperMapStruct {

    // Maps non-null fields from ProductRequestDTO to ProductModel
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductRequestDTO productRequest, @MappingTarget ProductModel existingProduct);
}