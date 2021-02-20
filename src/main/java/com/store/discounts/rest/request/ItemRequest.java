package com.store.discounts.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemRequest {

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "price")
	private double price;

	@JsonProperty(value = "type")
	private int type;

	public ItemRequest() {
		super();
	}

	public ItemRequest(String name, double price, int type) {
		super();
		this.name = name;
		this.price = price;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
