package com.store.discounts.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.discounts.jpa.model.Customer;
import com.store.discounts.jpa.model.Item;
import com.store.discounts.service.NetAmountService;
import com.store.discounts.utils.Constants;

@Service
public class NetAmountServiceImpl implements NetAmountService {

	@Override
	public double returnNetAmount(List<Item> items, Customer customer) {
		double discountedBill = 0d;
		final int discount = customer.getMembership().getDiscount();
		for (Item item : items) {
			if (discount > 0) {
//				if customer can have a discount
				if (item.getItemType().getValue() != Constants.GROCERIES_VALUE) {
					double markedPrice = item.getPrice();
					double discountRate = (100d - (double)discount);
					discountedBill += (discountRate*markedPrice)/100;
					continue;
				}
			}
//			if customer doesnt get a discount yet
			discountedBill += item.getPrice();
		}
		return (int) discountedBill;
	}

}
