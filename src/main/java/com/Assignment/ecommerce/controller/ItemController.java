package com.Assignment.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Assignment.ecommerce.exception.ResourceNotFoundException;
import com.Assignment.ecommerce.model.Item;
import com.Assignment.ecommerce.repository.ItemRepo;

@RestController
@RequestMapping("/api")
public class ItemController {

	@Autowired
	ItemRepo itemRepo;

	// Get all items
	@GetMapping("/items")
	public List<Item> getAllItems() {
		return itemRepo.findAll();
	}

	// Create a new item
	@PostMapping("/items")
	public Item createItem(@Valid @RequestBody Item note) {
		return itemRepo.save(note);
	}
	
	// Get a Single Item
	@GetMapping("/items/{id}")
	public Item getItemById(@PathVariable(value = "id") Long itemId) {
		return itemRepo.findById(itemId)
		.orElseThrow(() -> new ResourceNotFoundException("Item" + " id " + itemId + " not found"));
	}
	
	// Update a Item
	@PutMapping("/items/{id}")
	public Item updateItem(@PathVariable(value = "id") Long itemId,
	                                        @Valid @RequestBody Item itemDetails) {

		Item item = itemRepo.findById(itemId)
				.orElseThrow(() -> new ResourceNotFoundException("Item" + " id " + itemId + " not found"));

	    item.setAvailableQuantity(itemDetails.getAvailableQuantity());
	    item.setItemName(itemDetails.getItemName());

	    Item updatedItem = itemRepo.save(item);
	    return updatedItem;
	}
	
	// Delete a Item
	@DeleteMapping("/items/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable(value = "id") Long itemId) {
		Item item = itemRepo.findById(itemId)
				.orElseThrow(() -> new ResourceNotFoundException("Item" + " id " + itemId + " not found"));

		itemRepo.delete(item);

		return ResponseEntity.ok().build();
	}
}
