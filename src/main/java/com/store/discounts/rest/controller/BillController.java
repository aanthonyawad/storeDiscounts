package com.store.discounts.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.store.discounts.jpa.model.Customer;
import com.store.discounts.jpa.model.Item;
import com.store.discounts.jpa.model.Lookup;
import com.store.discounts.jpa.repository.ItemRepository;
import com.store.discounts.jpa.repository.LookupRepository;
import com.store.discounts.models.LoggingTask;
import com.store.discounts.rest.exception.IncompleteRequestException;
import com.store.discounts.rest.exception.ItemTypeNotFoundException;
import com.store.discounts.rest.exception.MembershipNotFoundException;
import com.store.discounts.rest.request.BillRequest;
import com.store.discounts.rest.request.ItemRequest;
import com.store.discounts.rest.response.BillResponse;
import com.store.discounts.rest.response.ErrorResponse;
import com.store.discounts.service.LogService;
import com.store.discounts.service.NetAmountService;
import com.store.discounts.utils.Constants;

@RestController
public class BillController {

	@Autowired
	LogService logsService;

	@Autowired
	NetAmountService netAmountService;

	@Autowired
	LookupRepository lookupRepository;

	@Autowired
	ItemRepository itemRepository;

	@PostMapping(path = "/bill")
	public ResponseEntity<BillResponse> calculateDiscount(@RequestBody BillRequest billRequest)
			throws MembershipNotFoundException, ItemTypeNotFoundException, IncompleteRequestException {

		Customer customer = new Customer(billRequest.getCustomer());
//		find customer type from predefined values
		Lookup customerMembershipLookup = lookupRepository
				.findByDataAndCategory(billRequest.getCustomer().getMembership(), Constants.CATEGORY_DISCOUNTS)
				.orElseThrow(() -> new MembershipNotFoundException("Membership not found!!"));
		customer.setMembership(customerMembershipLookup);

		List<Item> items = new ArrayList<>();
		for (ItemRequest ir : billRequest.getItems()) {
			Item item = new Item(ir);
//			find item type from predefined values
			Lookup itemTypeLookup = lookupRepository.findByDataAndCategory(ir.getType(), Constants.CATEGORY_ITEM_TYPES)
					.orElseThrow(() -> new ItemTypeNotFoundException("Item Type not found!!"));
			item.setItemType(itemTypeLookup);
			items.add(item);
		}

		logsService.addExecutor(
				logsService.getExecutor().submit(() -> new LoggingTask(Constants.LOG_BILL, customer, items)));

		double netPayableAmount = netAmountService.returnNetAmount(items, customer);
		BillResponse billResponse = new BillResponse(netPayableAmount);
		return new ResponseEntity<>(billResponse, HttpStatus.OK);
	}

	@RestControllerAdvice
	public class BillControllerAdvice {
		@ExceptionHandler(IncompleteRequestException.class)
		@ResponseStatus(value=HttpStatus.BAD_REQUEST)
		public ErrorResponse incompleteRequestException(IncompleteRequestException ex, WebRequest request) {
			return new ErrorResponse(1, ex.toString());
		}

		@ExceptionHandler(ItemTypeNotFoundException.class)
		@ResponseStatus(value=HttpStatus.BAD_REQUEST)
		public ErrorResponse itemTypeNotFoundException(ItemTypeNotFoundException ex, WebRequest request) {
			return new ErrorResponse(2, ex.toString());
		}

		@ExceptionHandler(MembershipNotFoundException.class)
		@ResponseStatus(value=HttpStatus.BAD_REQUEST)
		public ErrorResponse membershipNotFoundException(MembershipNotFoundException ex, WebRequest request) {
			return new ErrorResponse(3, ex.toString());
		}
	}

}
