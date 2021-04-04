package com.uc;

import com.uc.entities.Store;
import com.uc.services.StoreService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class StoreBean implements Serializable {

	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String location;

	@EJB
	private StoreService storeService;

	public List<Store> getStoreList() {
		return storeService.getStoreList();
	}

	public void addStore() {
		storeService.addStore(getFields());
	}

	//read
	public void getStore() {
		storeService.getStore(getFields());
	}

	//update
	public void updateStore() {
		storeService.updateStore(getFields());
	}

	//delete
	public void deleteStore() {
		storeService.deleteStore(getFields());
	}

	private Store getFields() {
		return Store.builder()
				.id(id)
				.name(name)
				.location(location)
				.build();
	}
}
