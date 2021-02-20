package com.store.discounts.rest.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BillRequest {
	
	@JsonProperty(value = "customer")
	private CustomerRequest customer;
	@JsonProperty(value = "items")
	private List<ItemRequest> items;
	
	public BillRequest(CustomerRequest customer, List<ItemRequest> items) {
		super();
		this.customer = customer;
		this.items = items;
	}
	
	public BillRequest() {
		super();
	}

	public CustomerRequest getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerRequest customer) {
		this.customer = customer;
	}
	public List<ItemRequest> getItems() {
		return items;
	}
	public void setItems(List<ItemRequest> items) {
		this.items = items;
	}
	
	
}
