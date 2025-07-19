package com.at.t.eCommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.at.t.eCommerce.dto.User_Register_DTO;
import com.at.t.eCommerce.model.UserModel;

@Mapper(componentModel = "spring")
public interface UserRegisterMapper {
    UserRegisterMapper INSTANCE = Mappers.getMapper(UserRegisterMapper.class);

    // Mapping User_Register_DTO to UserModel
    @Mapping(target = "id", ignore = true) // Ignoring ID since it's auto-generated
    @Mapping(target = "activeCart", ignore = true)
    @Mapping(target = "auditLogs", ignore = true)
    @Mapping(target = "createdDiscounts", ignore = true)
    @Mapping(target = "notifications", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "wishlist", ignore = true)
    @Mapping(target = "address", ignore = true) // Ignoring Address for now
    @Mapping(target = "role", ignore = true) // Ignoring role if not in DTO
    UserModel toEntity(User_Register_DTO dto);

    // Mapping UserModel to User_Register_DTO
    @Mapping(target = "password", ignore = true)
    User_Register_DTO toDTO(UserModel user);
}
