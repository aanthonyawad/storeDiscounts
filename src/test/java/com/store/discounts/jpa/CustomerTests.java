package com.store.discounts.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.store.discounts.jpa.model.Customer;
import com.store.discounts.jpa.repository.CustomerRepository;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class CustomerTests {
	// ======================================
	// = Attributes =
	// ======================================
	Customer validCustomer;
	@Autowired
	CustomerRepository customerRepository;

	void setUp() {
		// Creates a valid customer
		validCustomer = new Customer();
		validCustomer.setId(1);
		validCustomer.setName("Anthony");

	}

	@Test
	@Rollback(true)
	@Order(1)
	/**
	 * This test tries to create a customer with valid values.
	 */
	void testCreateValidCustomer() {
		this.setUp();
		assertEquals("Anthony", validCustomer.getName());
		assertEquals(1, validCustomer.getId());
		boolean okay = validCustomer.checkFields();
		assertTrue(okay, "Customer data is OK!");
	}

	@Test
	@Rollback(true)
	@Order(2)
	/**
	 * This test tries to create Customer with invalid values.
	 */
	void testCreateInvalidCustomer() {
		// Creates objects with empty values
		Customer customer = new Customer();
		customer.setId(0);
		boolean b = customer.checkFields();
		assertFalse(b, "Customer id should be bigger than zero!");
		customer.setName(
				"Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcmAwad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2");
		boolean b2 = customer.checkFields();
		assertFalse(b2, "Customer name should be smaller than 256!");
	}

	@Test
	@Rollback(false)
	@Order(3)
	/**
	 * This test tries to create an instance of the Customer object in the mem db
	 */
	void testCreateCustomer() {
		this.setUp();// create
		this.customerRepository.save(this.validCustomer);// add
		assertNotNull(validCustomer);// check
	}

	@Test
	@Rollback(false)
	@Order(4)
	/**
	 * This test tries to find an instance of the Customer object in the DB by value
	 * 
	 */
	void testFindCustomerByName() {
		String name = "Anthony";
		Optional<Customer> oCustomer = this.customerRepository.findByName(name);// find
		Customer customer = oCustomer.orElse(null);
		assertNotNull(customer);
		assertThat(customer.getName()).isEqualTo(name);// check
	}

	@Test
	@Rollback(true)
	@Order(5)
	/**
	 * This test tries to fail finding an instance of the Customer object in the mem
	 * db by value
	 */
	void testFindCustomerByNameNotExists() {
		String name = "Jhonny";
		Optional<Customer> oCustomer = this.customerRepository.findByName(name);// find
		Customer customer = oCustomer.orElse(null);
		assertNull(customer);// check
	}

	@Test
	@Rollback(true)
	@Order(6)
	/**
	 * This test tries to update an instance of the Customer object in the mem db
	 */
	void testUpdateCustomer() {
		String name = "Anthony";
		String newName = "Jhonny";
		Optional<Customer> oCustomer = this.customerRepository.findByName(name);// find
		Customer customer = oCustomer.orElse(null);
		assertNotNull(customer);
		customer.setName(newName);
		Customer savedCustomer = this.customerRepository.save(customer);// add
		assertThat(savedCustomer.getName()).isEqualTo(newName);// check
	}

	@Test
	@Rollback(true)
	@Order(7)
	/**
	 * This test tries to the instance of the Customer object in the mem db
	 */
	void testListCustomer() {
		Iterable<Customer> memIterable = this.customerRepository.findAll();
		assertNotNull(memIterable);
		assertThat(memIterable).size().isPositive();
	}

	@Test
	@Rollback(true)
	@Order(8)
	/**
	 * This test tries delete an instance of the Customer object in the mem db
	 */
	void testDeleteCustomer() {
		String name = "Anthony";
		Optional<Customer> oCustomer = this.customerRepository.findByName(name);// find
		Customer customer = oCustomer.orElse(null);
		assertNotNull(customer);
		this.customerRepository.delete(customer);
		boolean deleted = this.customerRepository.findById(customer.getId()).isPresent();
		assertFalse(deleted);
	}
}
