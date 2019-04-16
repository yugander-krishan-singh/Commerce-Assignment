package com.Assignment.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Assignment.ecommerce.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

	
}
