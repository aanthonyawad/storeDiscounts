package com.store.discounts.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerRequest {

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "membership")
	private int membership;
	
	public CustomerRequest(int id, String name, int membership) {
		super();
		this.id = id;
		this.name = name;
		this.membership = membership;
	}
	public CustomerRequest() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMembership() {
		return membership;
	}
	public void setMembership(int membership) {
		this.membership = membership;
	}
}
