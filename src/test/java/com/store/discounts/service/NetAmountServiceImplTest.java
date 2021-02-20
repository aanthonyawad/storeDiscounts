package com.store.discounts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.store.discounts.jpa.model.Customer;
import com.store.discounts.jpa.model.Item;
import com.store.discounts.jpa.model.ItemType;
import com.store.discounts.jpa.model.Membership;
import com.store.discounts.service.impl.NetAmountServiceImpl;
import com.store.discounts.utils.Constants;

public class NetAmountServiceImplTest {
	// ======================================
	// = Attributes =
	// ======================================
	NetAmountServiceImpl netAmountServiceImpl = new NetAmountServiceImpl();
	
	@Test
	/**
	 * This test tries to calculate net amount without groceries included for an employee
	 */
	public void testCalculateNetAmountForEmployee() {
		
		Customer customer= new Customer(); 
		customer.setId(1);
		customer.setName("Anthony");
		Membership membership = new Membership();
		membership.setCustomer(customer);
		membership.setId(1);
		membership.setName(Constants.EMPLOYEE_STRING);
		membership.setPriority(Constants.EMPLOYEE_VALUE);
		membership.setDiscount(Constants.EMPLOYEE_DISCOUNT);
		customer.setMembership(membership);
		
		List<Item> input = new ArrayList<Item>();
		double excpectedOutput = 70;
		Item item1 = new Item();
		item1.setId(1);
		item1.setName("cheese");
		item1.setPrice(50);
		ItemType itemType = new ItemType();
		itemType.setId(1);
		itemType.setName(Constants.ITEM_TYPE_OTHER);
		itemType.setValue(Constants.OTHER_VALUE);
		itemType.setItem(item1);
		item1.setItemType(itemType);
		
		
		Item item2 = new Item();
		item2.setId(2);
		item2.setName("chips");
		item2.setPrice(50);
		ItemType itemType2 = new ItemType();
		itemType2.setId(2);
		itemType2.setName(Constants.ITEM_TYPE_OTHER);
		itemType2.setValue(Constants.OTHER_VALUE);
		itemType.setItem(item2);
		item2.setItemType(itemType2);
		
		
		input.add(item1);
		input.add(item2);
		
		assertEquals(netAmountServiceImpl.returnNetAmount(input,customer), excpectedOutput);
	}
	

	@Test
	/**
	 * This test tries to calculate net amount with groceries included for an employee
	 */
	public void testCalculateNetAmountForEmployeeWithGroceries() {
		

		Customer customer= new Customer(); 
		customer.setId(1);
		customer.setName("Anthony");
		Membership membership = new Membership();
		membership.setCustomer(customer);
		membership.setId(1);
		membership.setName(Constants.EMPLOYEE_STRING);
		membership.setPriority(Constants.EMPLOYEE_VALUE);
		membership.setDiscount(Constants.EMPLOYEE_DISCOUNT);
		customer.setMembership(membership);
		
		List<Item> input = new ArrayList<Item>();
		double excpectedOutput = 120;
		Item item1 = new Item();
		item1.setId(1);
		item1.setName("cheese");
		item1.setPrice(50);
		ItemType itemType = new ItemType();
		itemType.setId(1);
		itemType.setName(Constants.ITEM_TYPE_GROCERIES);
		itemType.setValue(Constants.GROCERIES_VALUE);
		itemType.setItem(item1);
		item1.setItemType(itemType);
		
		
		Item item2 = new Item();
		item2.setId(2);
		item2.setName("chips");
		item2.setPrice(100);
		ItemType itemType2 = new ItemType();
		itemType2.setId(2);
		itemType2.setName(Constants.ITEM_TYPE_OTHER);
		itemType2.setValue(Constants.OTHER_VALUE);
		itemType2.setItem(item2);
		item2.setItemType(itemType2);
		
		
		input.add(item1);
		input.add(item2);
		
		assertEquals(netAmountServiceImpl.returnNetAmount(input,customer), excpectedOutput);
	}
	

	@Test
	/**
	 * This test tries to calculate net amount without groceries included for an affiliate
	 */
	public void testCalculateNetAmountForAffiliate() {
		
		Customer customer= new Customer(); 
		customer.setId(1);
		customer.setName("Anthony");
		Membership membership = new Membership();
		membership.setCustomer(customer);
		membership.setId(1);
		membership.setName(Constants.AFFILIATE_STRING);
		membership.setPriority(Constants.AFFILIATE_VALUE);
		membership.setDiscount(Constants.AFFILIATE_DISCOUNT);
		customer.setMembership(membership);
		
		List<Item> input = new ArrayList<Item>();
		double excpectedOutput = 90;
		Item item1 = new Item();
		item1.setId(1);
		item1.setName("cheese");
		item1.setPrice(50);
		ItemType itemType = new ItemType();
		itemType.setId(1);
		itemType.setName(Constants.ITEM_TYPE_OTHER);
		itemType.setValue(Constants.OTHER_VALUE);
		itemType.setItem(item1);
		item1.setItemType(itemType);
		
		
		Item item2 = new Item();
		item2.setId(2);
		item2.setName("chips");
		item2.setPrice(50);
		ItemType itemType2 = new ItemType();
		itemType2.setId(2);
		itemType2.setName(Constants.ITEM_TYPE_OTHER);
		itemType2.setValue(Constants.OTHER_VALUE);
		itemType2.setItem(item2);
		item2.setItemType(itemType2);
		
		
		input.add(item1);
		input.add(item2);
		
		assertEquals(netAmountServiceImpl.returnNetAmount(input,customer), excpectedOutput);
	}
	

	@Test
	/**
	 * This test tries to calculate net amount with groceries included for an affiliate
	 */
	public void testCalculateNetAmountForAffiliateWithGroceries() {
		

		Customer customer= new Customer(); 
		customer.setId(1);
		customer.setName("Anthony");
		Membership membership = new Membership();
		membership.setCustomer(customer);
		membership.setId(1);
		membership.setName(Constants.AFFILIATE_STRING);
		membership.setPriority(Constants.AFFILIATE_VALUE);
		membership.setDiscount(Constants.AFFILIATE_DISCOUNT);
		customer.setMembership(membership);
		
		List<Item> input = new ArrayList<Item>();
		double excpectedOutput = 140;
		Item item1 = new Item();
		item1.setId(1);
		item1.setName("cheese");
		item1.setPrice(50);
		ItemType itemType = new ItemType();
		itemType.setId(1);
		itemType.setName(Constants.ITEM_TYPE_GROCERIES);
		itemType.setValue(Constants.GROCERIES_VALUE);
		itemType.setItem(item1);
		item1.setItemType(itemType);
		
		
		Item item2 = new Item();
		item2.setId(2);
		item2.setName("chips");
		item2.setPrice(100);
		ItemType itemType2 = new ItemType();
		itemType2.setId(2);
		itemType2.setName(Constants.ITEM_TYPE_OTHER);
		itemType2.setValue(Constants.OTHER_VALUE);
		itemType2.setItem(item2);
		item2.setItemType(itemType2);
		
		
		input.add(item1);
		input.add(item2);
		
		assertEquals(netAmountServiceImpl.returnNetAmount(input,customer), excpectedOutput);
	}
	

	@Test
	/**
	 * This test tries to calculate net amount without groceries included for a loyal customer
	 */
	public void testCalculateNetAmountForLoyalCustomer() {
		
		Customer customer= new Customer(); 
		customer.setId(1);
		customer.setName("Anthony");
		Membership membership = new Membership();
		membership.setCustomer(customer);
		membership.setId(1);
		membership.setName(Constants.LOYAL_CUSTOMER_STRING);
		membership.setPriority(Constants.LOYAL_CUSTOMER_VALUE);
		membership.setDiscount(Constants.LOYAL_CUSTOMER_DISCOUNT);
		customer.setMembership(membership);
		
		List<Item> input = new ArrayList<Item>();
		double excpectedOutput = 95;
		Item item1 = new Item();
		item1.setId(1);
		item1.setName("cheese");
		item1.setPrice(50);
		ItemType itemType = new ItemType();
		itemType.setId(1);
		itemType.setName(Constants.ITEM_TYPE_OTHER);
		itemType.setValue(Constants.OTHER_VALUE);
		itemType.setItem(item1);
		item1.setItemType(itemType);
		
		
		Item item2 = new Item();
		item2.setId(2);
		item2.setName("chips");
		item2.setPrice(50);
		ItemType itemType2 = new ItemType();
		itemType2.setId(2);
		itemType2.setName(Constants.ITEM_TYPE_OTHER);
		itemType2.setValue(Constants.OTHER_VALUE);
		itemType2.setItem(item2);
		item2.setItemType(itemType2);
		
		
		input.add(item1);
		input.add(item2);
		
		assertEquals(netAmountServiceImpl.returnNetAmount(input,customer), excpectedOutput);
	}
	

	@Test
	/**
	 * This test tries to calculate net amount with groceries included for a loyal customer
	 */
	public void testCalculateNetAmountForLoyalCustomerWithGroceries() {
		

		Customer customer= new Customer(); 
		customer.setId(1);
		customer.setName("Anthony");
		Membership membership = new Membership();
		membership.setCustomer(customer);
		membership.setId(1);
		membership.setName(Constants.LOYAL_CUSTOMER_STRING);
		membership.setPriority(Constants.LOYAL_CUSTOMER_VALUE);
		membership.setDiscount(Constants.LOYAL_CUSTOMER_DISCOUNT);
		customer.setMembership(membership);
		
		List<Item> input = new ArrayList<Item>();
		double excpectedOutput = 145;
		Item item1 = new Item();
		item1.setId(1);
		item1.setName("cheese");
		item1.setPrice(50);
		ItemType itemType = new ItemType();
		itemType.setId(1);
		itemType.setName(Constants.ITEM_TYPE_GROCERIES);
		itemType.setValue(Constants.GROCERIES_VALUE);
		itemType.setItem(item1);
		item1.setItemType(itemType);
		
		
		Item item2 = new Item();
		item2.setId(2);
		item2.setName("chips");
		item2.setPrice(100);
		ItemType itemType2 = new ItemType();
		itemType2.setId(2);
		itemType2.setName(Constants.ITEM_TYPE_OTHER);
		itemType2.setValue(Constants.OTHER_VALUE);
		itemType2.setItem(item2);
		item2.setItemType(itemType2);
		
		
		input.add(item1);
		input.add(item2);
		
		assertEquals(netAmountServiceImpl.returnNetAmount(input,customer), excpectedOutput);
	}


	@Test
	/**
	 * This test tries to calculate net amount without groceries included for an no discount customer
	 */
	public void testCalculateNetAmountForNoDiscountCustomer() {
		
		Customer customer= new Customer(); 
		customer.setId(1);
		customer.setName("Anthony");
		Membership membership = new Membership();
		membership.setCustomer(customer);
		membership.setId(1);
		membership.setName(Constants.REGULAR_CUSTOMER_STRING);
		membership.setPriority(Constants.REGULAR_CUSTOMER_VALUE);
		membership.setDiscount(Constants.REGULAR_CUSTOMER_DISCOUNT);
		customer.setMembership(membership);
		
		List<Item> input = new ArrayList<Item>();
		double excpectedOutput = 100;
		Item item1 = new Item();
		item1.setId(1);
		item1.setName("cheese");
		item1.setPrice(50);
		ItemType itemType = new ItemType();
		itemType.setId(1);
		itemType.setName(Constants.ITEM_TYPE_OTHER);
		itemType.setValue(Constants.OTHER_VALUE);
		itemType.setItem(item1);
		item1.setItemType(itemType);
		
		
		Item item2 = new Item();
		item2.setId(2);
		item2.setName("chips");
		item2.setPrice(50);
		ItemType itemType2 = new ItemType();
		itemType2.setId(2);
		itemType2.setName(Constants.ITEM_TYPE_OTHER);
		itemType2.setValue(Constants.OTHER_VALUE);
		itemType2.setItem(item2);
		item2.setItemType(itemType2);
		
		
		input.add(item1);
		input.add(item2);
		
		assertEquals(netAmountServiceImpl.returnNetAmount(input,customer), excpectedOutput);
	}
	

	@Test
	/**
	 * This test tries to calculate net amount with groceries included for an no discount customer
	 */
	public void testCalculateNetAmountForNoDiscountCustomerWithGroceries() {
		

		Customer customer= new Customer(); 
		customer.setId(1);
		customer.setName("Anthony");
		Membership membership = new Membership();
		membership.setCustomer(customer);
		membership.setId(1);
		membership.setName(Constants.REGULAR_CUSTOMER_STRING);
		membership.setPriority(Constants.REGULAR_CUSTOMER_VALUE);
		membership.setDiscount(Constants.REGULAR_CUSTOMER_DISCOUNT);
		customer.setMembership(membership);
		
		List<Item> input = new ArrayList<Item>();
		double excpectedOutput = 150;
		
		Item item1 = new Item();
		item1.setId(1);
		item1.setName("cheese");
		item1.setPrice(50);
		ItemType itemType = new ItemType();
		itemType.setId(1);
		itemType.setName(Constants.ITEM_TYPE_GROCERIES);
		itemType.setValue(Constants.GROCERIES_VALUE);
		itemType.setItem(item1);
		item1.setItemType(itemType);
		
		
		Item item2 = new Item();
		item2.setId(2);
		item2.setName("chips");
		item2.setPrice(100);
		ItemType itemType2 = new ItemType();
		itemType2.setId(2);
		itemType2.setName(Constants.ITEM_TYPE_OTHER);
		itemType2.setValue(Constants.OTHER_VALUE);
		itemType2.setItem(item2);
		item2.setItemType(itemType2);
		
		
		input.add(item1);
		input.add(item2);
		
		assertEquals(netAmountServiceImpl.returnNetAmount(input,customer), excpectedOutput);
	}


}
