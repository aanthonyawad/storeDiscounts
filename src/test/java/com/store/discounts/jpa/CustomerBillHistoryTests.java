package com.store.discounts.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.store.discounts.jpa.model.CustomerBillHistory;
import com.store.discounts.jpa.repository.CustomerBillHistoryRepository;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class CustomerBillHistoryTests {

	// ======================================
	// = Attributes =
	// ======================================
	CustomerBillHistory validCustomerBillHistory;
	@Autowired
	CustomerBillHistoryRepository customerBillHistoryRepository;
	

	void setUp() {
		// Creates a valid customer
		validCustomerBillHistory = new CustomerBillHistory();
		validCustomerBillHistory.setId(1);
		Calendar myCalendar = new GregorianCalendar(2021, 2, 20);
		Date date = myCalendar.getTime();
		validCustomerBillHistory.setInsertDate(date);
	}
	
	@Test
	@Rollback(true)
	@Order(1)
	/**
	 * This test tries to create a CustomerBillHistory with valid values.
	 */
	void testCreateValidCustomerBillHistory() {
		this.setUp();
		Calendar myCalendar = new GregorianCalendar(2021, 2, 20);
		Date date = myCalendar.getTime();
		assertEquals(1, validCustomerBillHistory.getId());
		assertEquals(date.toString(), validCustomerBillHistory.getInsertDate().toString());
		boolean okay = validCustomerBillHistory.checkFields();
		assertTrue(okay, "CustomerBillHistory data is OK!");
	}

	@Test
	@Rollback(true)
	@Order(2)
	/**
	 * This test tries to create CustomerBillHistory with invalid values.
	 */
	void testCreateInvalidCustomer() {
		// Creates objects with empty values
		Calendar myCalendar = new GregorianCalendar(2021, 2, 20);
		Date date = myCalendar.getTime();
		CustomerBillHistory customerBillHistory = new CustomerBillHistory();
		customerBillHistory.setId(0);
		boolean b = customerBillHistory.checkFields();
		assertFalse(b, "CustomerBillHistory id should be bigger than zero!");
		customerBillHistory.setInsertDate(null);
		boolean b2 = customerBillHistory.checkFields();
		assertFalse(b2, "CustomerBillHistory date should not be null!");
	}

	
}
