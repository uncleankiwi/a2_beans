package com.uc;

import com.uc.entities.Store;
import com.uc.services.StoreService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class StoreBean implements Serializable {

	@Getter @Setter
	private Long id;

	@Getter @Setter
	private String name;

	@Getter @Setter
	private String location;

	@EJB //EJB: connect to @Remote service
	private StoreService storeService;

	//create
	public void addStore() {
		try {
			storeService.addStore(getFields());
		}
		catch (Exception e) {
			showMessage("Error adding store:\n" + e.getMessage());
		}
	}

	//read all
	public List<Store> getStoreList() {
		return storeService.getStoreList();
	}

	//read
	public void getStore() {
		try {
			Store store = storeService.getStore(getFields());
			if (store == null) throw new Exception("No store with that id exists.");
			setFields(store);
		}
		catch (Exception e) {
			showMessage("Error getting store:\n" + e.getMessage());
		}
	}

	//update
	public void updateStore() {
		try {
			storeService.updateStore(getFields());
		}
		catch (Exception e) {
			showMessage("Error updating store:\n" + e.getMessage());
		}
	}

	//delete
	public void deleteStore() {
		try {
			storeService.deleteStore(getFields());
		}
		catch (Exception e) {
			showMessage("Error deleting store:\n" + e.getMessage());
		}
	}

	//redirect to Inventory.xhtml to view a particular store's inventory
	public String viewStoreInventory(Long storeId){
		return "Inventory?faces-redirect=true&id=" + storeId;
	}

	//populate view elements from object
	private void setFields(Store store) {
		this.id = store.getId();
		this.name = store.getName();
		this.location = store.getLocation();
	}

	//create object from view elements
	private Store getFields() {
		if (id == null) id = -1L;

		return Store.builder()
				.id(id)
				.name(name)
				.location(location)
				.build();
	}

	public void clear() {
		this.id = null;
		this.name = "";
		this.location = "";
	}

	public void showMessage(String msg) {
		FacesMessage dialogue = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", msg);
		PrimeFaces.current().dialog().showMessageDynamic(dialogue);
	}
}
