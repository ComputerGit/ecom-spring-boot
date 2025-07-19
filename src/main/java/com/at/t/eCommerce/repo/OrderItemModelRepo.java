package com.at.t.eCommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.at.t.eCommerce.model.OrderItemModel;

@Repository
public interface OrderItemModelRepo extends JpaRepository<OrderItemModel, Long> {

}
