package com.uc;

import com.uc.entities.Inventory;
import com.uc.services.InventoryService;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SessionScoped
@Named
public class InventoryBean implements Serializable {
	@Getter @Setter
	private Long id;

	@NotNull
	@Getter	@Setter
	private String name;

	@NotNull
	@Getter	@Setter
	private String sport;

	@NotNull
	@Getter	@Setter
	private int quantity;

	@NotNull
	@Getter	@Setter
	private float price;

	@Getter @Setter
	private Date addedDate;

	@Getter @Setter
	private Date updatedDate;

	@EJB
	private InventoryService inventoryService;

	public List<Inventory> getInventoryList() {
		return inventoryService.getInventoryList();
	}

	public void addInventory() {
		inventoryService.addStore(getFields());
	}

	//read
	public void getInventory() {
		setFields(inventoryService.getInventory(getFields()));
	}

	//update
	public void updateInventory() {
		System.out.println(getFields());	//TODO
		inventoryService.updateInventory(getFields());
	}

	//delete
	public void deleteInventory() {
		inventoryService.deleteInventory(getFields());
	}

	private Inventory getFields() {
		if (id == null) id = -1L;

		return Inventory.builder()
				.id(id)
				.name(name)
				.sport(sport)
				.quantity(quantity)
				.price(price)
				.build();
	}

	private void setFields(Inventory inventory) {
		this.id = inventory.getId();
		this.name = inventory.getName();
		this.sport = inventory.getSport();
		this.quantity = inventory.getQuantity();
		this.price = inventory.getPrice();
	}

	public void clear() {
		this.id = null;
		this.name = "";
		this.sport = "";
		this.quantity = 0;
		this.price = 0;
	}
}
