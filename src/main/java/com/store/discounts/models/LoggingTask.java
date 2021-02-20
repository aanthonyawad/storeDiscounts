package com.store.discounts.models;

import java.util.List;

import com.store.discounts.jpa.model.Customer;
import com.store.discounts.jpa.model.Item;

public class LoggingTask extends FinishedTask {
	private Customer customer;
	private List<Item> items;
	public LoggingTask(int taskType) {
		super(taskType);
	}
	public LoggingTask(int taskType, Customer customer, List<Item> items) {
		super(taskType);
		this.customer = customer;
		this.items = items;
	}
	public Customer getCustomer() {
		return customer;
	}
	public List<Item> getItems() {
		return items;
	}
	

}
