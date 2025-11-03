package com.at.t.eCommerce.repo;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.at.t.eCommerce.model.CoreUser;


@Repository
public interface CoreUserRepo extends JpaRepository<CoreUser, UUID>{

    Optional<CoreUser> findByEmail(String email);
    
    Boolean existsByEmail(String email);
    
    Boolean existsByPhone(String phone);

//    @Transactional
//    UserModel deleteByid(Long id);
    
    
}
