package com.store.discounts.service;

import java.util.List;

import com.store.discounts.jpa.model.Customer;
import com.store.discounts.jpa.model.Item;

public interface NetAmountService {
	double returnNetAmount(List<Item> items, Customer customer);
}
