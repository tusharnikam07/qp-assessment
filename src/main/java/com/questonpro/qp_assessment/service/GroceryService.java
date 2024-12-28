package com.questonpro.qp_assessment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.questonpro.qp_assessment.repository.GroceryItem;
import com.questonpro.qp_assessment.repository.GroceryRepository;
import com.questonpro.qp_assessment.repository.OrderItem;
import com.questonpro.qp_assessment.repository.OrderRepository;
import com.questonpro.qp_assessment.repository.Orders;

@Service
public class GroceryService {
	private final GroceryRepository groceryRepository;
	private final OrderRepository orderRepository;

	public GroceryService(GroceryRepository groceryRepository, OrderRepository orderRepository) {
		this.groceryRepository = groceryRepository;
		this.orderRepository = orderRepository;
	}

	public GroceryItem addItem(GroceryItem item) {
		if (Objects.isNull(item) || Objects.isNull(item.getName()) || Objects.isNull(item.getPrice())
				|| Objects.isNull(item.getInventory())) {
			throw new IllegalArgumentException("Required attributes are unavailble in request");
		}
		return groceryRepository.save(item);
	}

	public List<GroceryItem> getAllItems() {
		return groceryRepository.findAll();
	}

	public GroceryItem updateItem(Long id, GroceryItem updatedItem) {
		if (id == 0) {
			throw new IllegalArgumentException("Required attributes are unavailble in request");
		}

		GroceryItem item = groceryRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Item not found"));
		if (updatedItem.getName() != null && !updatedItem.getName().isEmpty()) {
			item.setName(updatedItem.getName());
		}
		if (updatedItem.getPrice() != null) {
			item.setPrice(updatedItem.getPrice());
		}
		if (updatedItem.getInventory() != null) {
			item.setInventory(updatedItem.getInventory());
		}
		return groceryRepository.save(item);
	}

	public void deleteItem(Long id) {
		groceryRepository.deleteById(id);
	}

	public Orders placeOrder(List<OrderItem> orderItems) {
		if (orderItems.size() == 0) {
			throw new IllegalArgumentException("Required attributes are unavailble in request");
		}
		Orders order = new Orders();
		List<GroceryItem> updatedGroceryItems = new ArrayList<GroceryItem>();
		order.setOrderItems(orderItems);
		orderItems.forEach(item -> {
			GroceryItem groceryItem = groceryRepository.findById(item.getItemId())
					.orElseThrow(() -> new NoSuchElementException("Item not found"));
			if (groceryItem.getInventory() < item.getQuantity()) {
				throw new IllegalStateException("Insufficient inventory for item: " + groceryItem.getName());
			}
			groceryItem.setInventory(groceryItem.getInventory() - item.getQuantity());
			updatedGroceryItems.add(groceryItem);
		});
		groceryRepository.saveAll(updatedGroceryItems);
		return orderRepository.save(order);
	}
}