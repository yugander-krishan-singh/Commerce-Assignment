package com.Assignment.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Assignment.ecommerce.model.Item;
import com.Assignment.ecommerce.model.Order;

public interface ItemRepo extends JpaRepository<Item, Long> {

}
