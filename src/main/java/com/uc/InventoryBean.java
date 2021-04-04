package com.uc;

import com.uc.entities.Inventory;
import com.uc.services.InventoryService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class InventoryBean implements Serializable {
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String sport;

	@NotNull
	private int quantity;

	@NotNull
	private float price;

	@EJB
	private InventoryService inventoryService;

	public List<Inventory> getInventoryList() {
		return inventoryService.getInventoryList();
	}

	public void addStore() {
		inventoryService.addStore(getFields());
	}

	//read
	public void getInventory() {
		inventoryService.getInventory(getFields());
	}

	//update
	public void updateInventory() {
		inventoryService.updateInventory(getFields());
	}

	//delete
	public void deleteInventory() {
		inventoryService.deleteInventory(getFields());
	}

	private Inventory getFields() {
		return Inventory.builder()
				.id(id)
				.name(name)
				.sport(sport)
				.quantity(quantity)
				.price(price)
				.build();
	}
}
