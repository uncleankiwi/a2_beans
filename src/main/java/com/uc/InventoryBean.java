package com.uc;

import com.uc.entities.Inventory;
import com.uc.entities.Store;
import com.uc.services.InventoryService;
import com.uc.services.StoreService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
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

	@Setter
	private Store store;

	@EJB //EJB: connect to @Remote service
	private InventoryService inventoryService;

	@EJB //EJB: connect to @Remote service
	private StoreService storeService;

	//create
	public void addInventory() {
		try {
			inventoryService.addStore(getFields());
		}
		catch (Exception e) {
			showMessage("Error adding inventory item:\n" + e.getMessage());
		}
	}

	//read all
	public List<Inventory> getInventoryList() {
		initStoreInfo();
		return inventoryService.getStoreInventory(storeId);
	}

	//read
	public void getInventory() {
		try {
			Inventory inv = inventoryService.getInventory(getFields());
			if (inv == null) throw new Exception("No item with that id exists.");
			setFields(inv);

		}
		catch (Exception e) {
			showMessage("Error getting inventory item:\n" + e.getMessage());
		}
	}

	//update
	public void updateInventory() {
		try {
			inventoryService.updateInventory(getFields());
			System.out.println(getFields());
		}
		catch (Exception e) {
			showMessage("Error updating inventory item:\n" + e.getMessage());
		}
	}

	//delete
	public void deleteInventory() {
		try {
			inventoryService.deleteInventory(getFields());
		}
		catch (Exception e) {
			showMessage("Error deleting inventory item:\n" + e.getMessage());
		}
	}

	//build object from view elements
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

	//loading view elements from object
	private void setFields(Inventory inventory) {
		this.id = inventory.getId();
		this.name = inventory.getName();
		this.sport = inventory.getSport();
		this.quantity = inventory.getQuantity();
		this.price = inventory.getPrice();
	}

	//loading store from jsf-view param in url
	private void initStoreInfo() {
		this.store = storeService.getStoreFromId(this.storeId);
	}

	//lazy retrieval of store since storeId is loaded late
	public Store getStore() {
		initStoreInfo();
		return this.store;
	}

	public void clear() {
		this.id = null;
		this.name = "";
		this.sport = "";
		this.quantity = 0;
		this.price = 0;
	}

	public void showMessage(String msg) {
		FacesMessage dialogue = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", msg);
		PrimeFaces.current().dialog().showMessageDynamic(dialogue);
	}
}
