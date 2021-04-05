package com.uc;

import com.uc.entities.Store;
import com.uc.services.StoreService;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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
		setFields(storeService.getStore(getFields()));
	}

	//update
	public void updateStore() {
		storeService.updateStore(getFields());
	}

	//delete
	public void deleteStore() {
		storeService.deleteStore(getFields());
	}

	private void setFields(Store store) {
		this.id = store.getId();
		this.name = store.getName();
		this.location = store.getLocation();
	}

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
}
