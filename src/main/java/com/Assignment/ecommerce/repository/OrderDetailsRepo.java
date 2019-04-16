package com.Assignment.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Assignment.ecommerce.model.OrderDetails;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Long> {

}
