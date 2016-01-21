package com.learning.spring.Service;

import java.util.List;

import com.learning.spring.Model.Inventory;

public interface InventoryService {

	public void addProduct(Inventory item);

	public void updateProduct(Inventory item);

	public void purchaseProduct(int inventoryId, String status);

	public Inventory getProduct(int inventoryId);

	public List getAllProducts();

	public void updateCartField(int inventoryId, String isInCart);

	public List getAllProductsInCart();
}
