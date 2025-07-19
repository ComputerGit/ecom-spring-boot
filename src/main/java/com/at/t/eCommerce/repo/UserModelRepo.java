package com.at.t.eCommerce.repo;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.at.t.eCommerce.model.UserModel;
import jakarta.transaction.Transactional;

@Repository
public interface UserModelRepo extends JpaRepository<UserModel, Long>{

    Optional<UserModel> findByUserName(String userName);
    
    Boolean existsByEmail(String email);
    
    Boolean existsByPhone(String phone);

    @Transactional
    void deleteByUserName(String username);

    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.dob = :dob WHERE u.userName = :username")
	int updateUserByDOB(@Param("dob") LocalDate dob , @Param("username") String username);
	

//    @Transactional
//    UserModel deleteByid(Long id);
    
    
}
