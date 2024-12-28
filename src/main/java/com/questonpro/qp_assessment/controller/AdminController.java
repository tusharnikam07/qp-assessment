package com.questonpro.qp_assessment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questonpro.qp_assessment.repository.GroceryItem;
import com.questonpro.qp_assessment.service.GroceryService;

@RestController
@RequestMapping("/api/v1/admin/grocery-items")
public class AdminController {
	private final GroceryService groceryService;

	public AdminController(GroceryService groceryService) {
		this.groceryService = groceryService;
	}

	@PostMapping
	public GroceryItem addGroceryItem(@RequestBody GroceryItem item) {
		return groceryService.addItem(item);
	}

	@GetMapping
	public List<GroceryItem> viewGroceryItems() {
		return groceryService.getAllItems();
	}

	@PutMapping("/{id}")
	public GroceryItem updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItem updatedItem) {
		return groceryService.updateItem(id, updatedItem);
	}

	@DeleteMapping("/{id}")
	public void removeGroceryItem(@PathVariable Long id) {
		groceryService.deleteItem(id);
	}
}