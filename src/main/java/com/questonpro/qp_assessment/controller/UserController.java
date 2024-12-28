package com.questonpro.qp_assessment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questonpro.qp_assessment.repository.GroceryItem;
import com.questonpro.qp_assessment.repository.Orders;
import com.questonpro.qp_assessment.repository.OrderItem;
import com.questonpro.qp_assessment.service.GroceryService;

@RestController
@RequestMapping("/api/v1/user/grocery-items")
public class UserController {
	private final GroceryService groceryService;

	public UserController(GroceryService groceryService) {
		this.groceryService = groceryService;
	}

	@GetMapping
	public List<GroceryItem> viewGroceryItems() {
		return groceryService.getAllItems();
	}

	@PostMapping("/order")
	public Orders placeOrder(@RequestBody List<OrderItem> orderItems) {
		return groceryService.placeOrder(orderItems);
	}
}
