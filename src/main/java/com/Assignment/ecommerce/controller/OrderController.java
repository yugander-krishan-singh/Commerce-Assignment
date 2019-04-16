package com.Assignment.ecommerce.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.Assignment.ecommerce.model.Item;
import com.Assignment.ecommerce.model.Order;
import com.Assignment.ecommerce.model.OrderDetails;
import com.Assignment.ecommerce.repository.ItemRepo;
import com.Assignment.ecommerce.repository.OrderDetailsRepo;
import com.Assignment.ecommerce.repository.OrderRepo;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	OrderDetailsRepo orderDetailsRepo;
	
	@Autowired
	ItemRepo itemRepo;
	
	// Get all orders
	@GetMapping("/orders")
	public List<Order> getAllOrders() {
	    return orderRepo.findAll();
	}
	
	// Create a new order
	@PostMapping("/orders")
	public Order createOrder(@Valid @RequestBody Order order) {
		// Order details are created first and then the order
		/*
		 * Set<OrderDetails> set = order.getOrderDetails(); set.forEach(orderDetail ->
		 * orderDetailsRepo.save(orderDetail)); return orderRepo.save(order);
		 */
		Set<OrderDetails> orderDetails = order.getOrderDetails();
		
		orderDetails.forEach(o -> {
			
			Optional<Item> option = itemRepo.findById(o.getItemId());
			 if(option.isEmpty()) {
				 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item with id "+o.getItemId() + " not found");
			 }else if(option.get().getAvailableQuantity()<o.getQuantityOrdered()) {
				 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity ordered for item id "+o.getItemId() + " is not avaialble");
			 }
		});
		
		Order newOrder = orderRepo.save(order);
		orderDetails.forEach(line -> {
			line.setOrder(newOrder);
			orderDetailsRepo.save(line);
			Item item = itemRepo.findById(line.getItemId()).get();
			item.setAvailableQuantity(item.getAvailableQuantity()-line.getQuantityOrdered());
			itemRepo.save(item);
		});
		return newOrder; 
	}
	
	// Get a Single Order
	@GetMapping("/orders/{id}")
	public Order getNoteById(@PathVariable(value = "id") Long orderId) {
		Optional<Order> option = orderRepo.findById(orderId);
		if(option.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order with id "+orderId + " not found");
		}
	    return option.get();
	}
	
	// Update an Order
	@PutMapping("/orders/{id}")
	public Order updateOrder(@PathVariable(value = "id") Long orderId, @Valid @RequestBody Order orderDetails) {

		Order order = orderRepo.findById(orderId).get();
		// .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

		

		Order updatedOrder = orderRepo.save(order);
		return updatedOrder;
	}
	
	// Delete a Order
	@DeleteMapping("/orders/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") Long orderId) {
		Optional<Order> option = orderRepo.findById(orderId);
		
		if (option.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order with id " + orderId + " not found");
		}
		
	    Order order = option.get();
	    orderRepo.delete(order);
	    return ResponseEntity.ok().build();
	}
}
