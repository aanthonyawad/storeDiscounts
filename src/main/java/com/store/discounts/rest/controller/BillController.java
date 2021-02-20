package com.store.discounts.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.store.discounts.jpa.model.Customer;
import com.store.discounts.jpa.model.Item;
import com.store.discounts.jpa.model.Lookup;
import com.store.discounts.jpa.repository.CustomerRepository;
import com.store.discounts.jpa.repository.ItemRepository;
import com.store.discounts.jpa.repository.LookupRepository;
import com.store.discounts.rest.exception.ItemTypeNotFoundException;
import com.store.discounts.rest.exception.MembershipNotFoundException;
import com.store.discounts.rest.request.BillRequest;
import com.store.discounts.rest.request.ItemRequest;
import com.store.discounts.rest.response.BillResponse;
import com.store.discounts.service.NetAmountService;
import com.store.discounts.utils.Constants;

@RestController
public class BillController {

	@Autowired
	NetAmountService netAmountService;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	LookupRepository lookupRepository;

	@Autowired
	ItemRepository itemRepository;
	@GetMapping(path = "/")
	public ResponseEntity<BillResponse> calculateDiscount(@RequestBody BillRequest billRequest)
			throws MembershipNotFoundException, ItemTypeNotFoundException {
		Customer customer = new Customer(billRequest.getCustomer());
//		find customer type from predefined values
		Lookup custumerMembershipLookup = lookupRepository
				.findByDataAndCategory(billRequest.getCustomer().getMembership(), Constants.CATEGORY_DISCOUNTS)
				.orElseThrow(() -> new MembershipNotFoundException("Membership not found!!"));
		customer.setMembership(custumerMembershipLookup);
		
		List<Item> items = new ArrayList<Item>();
		for (ItemRequest ir : billRequest.getItems()) {
			Item item = new Item(ir);
//			find item type from predefined values
			Lookup itemTypeLookup = lookupRepository.findByDataAndCategory(ir.getType(), Constants.CATEGORY_ITEM_TYPES)
					.orElseThrow(() -> new ItemTypeNotFoundException("Item Type not found!!"));
			item.setItemType(itemTypeLookup);
			items.add(item);
		}
		double netPayableAmount = netAmountService.returnNetAmount(items, customer);
		BillResponse billResponse = new BillResponse(netPayableAmount);
		return new ResponseEntity<BillResponse>(billResponse,HttpStatus.OK);
	}

	@RestControllerAdvice
	public class BillControllerAdvice {

	}

}
