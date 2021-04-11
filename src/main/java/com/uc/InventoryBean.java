package com.uc;

import com.uc.entities.Inventory;
import com.uc.entities.Store;
import com.uc.services.InventoryService;
import com.uc.services.StoreService;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SessionScoped
@Named
public class InventoryBean implements Serializable {
	@Getter @Setter
	private Long id;

	@Getter	@Setter
	private String name;

	@Getter	@Setter
	private String sport;

	@Getter	@Setter
	private int quantity;

	@Getter	@Setter
	private float price;

	@Getter @Setter
	private Date addedDate;

	@Getter @Setter
	private Date updatedDate;

	@Getter @Setter
	private Long storeId;

	@Getter @Setter
	private Store store;

	@EJB
	private InventoryService inventoryService;

	@EJB
	private StoreService storeService;

	public List<Inventory> getInventoryList() {
		initStoreInfo();
		return inventoryService.getStoreInventory(storeId);
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
				.store(store)
				.build();
	}

	private void setFields(Inventory inventory) {
		this.id = inventory.getId();
		this.name = inventory.getName();
		this.sport = inventory.getSport();
		this.quantity = inventory.getQuantity();
		this.price = inventory.getPrice();
	}

	private void initStoreInfo() {
		this.store = storeService.getStoreFromId(this.storeId);
	}

	public void clear() {
		this.id = null;
		this.name = "";
		this.sport = "";
		this.quantity = 0;
		this.price = 0;
	}
}
