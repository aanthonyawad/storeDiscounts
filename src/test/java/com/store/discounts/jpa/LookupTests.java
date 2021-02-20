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

import com.store.discounts.jpa.model.Lookup;
import com.store.discounts.jpa.repository.LookupRepository;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class LookupTests {
	// ======================================
	// = Attributes =
	// ======================================
	Lookup validLookup;
	@Autowired
	LookupRepository lookupRepository;

	public void setUp() {
		// Creates a valid customer
		validLookup = new Lookup();
		validLookup.setId(50);
		validLookup.setName("Other");
		validLookup.setData(50);
		validLookup.setCategory(40);
	}

	@Test
	@Rollback(true)
	@Order(1)
	/**
	 * This test tries to create an Lookup with valid values.
	 */
	void testCreateValidLookup() {
		this.setUp();
		assertEquals(50, validLookup.getId());
		assertEquals(50, validLookup.getData());
		assertEquals(40, validLookup.getCategory());
		assertEquals("Other", validLookup.getName());
		boolean okay = validLookup.checkFields();
		assertTrue(okay, "Lookup data is OK!");
	}

	@Test
	@Rollback(true)
	@Order(2)
	/**
	 * This test tries to create Lookup with invalid values.
	 */
	void testCreateInvalidLookup() {
		// Creates objects with empty values
		Lookup lookup = new Lookup();
		lookup.setId(0);
		boolean b = lookup.checkFields();
		assertFalse(b, "Lookup id should be bigger than zero!");
		lookup.setName(
				"Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcmAwad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2");
		boolean b2 = lookup.checkFields();
		assertFalse(b2, "Lookup name should be smaller than 256!");
		lookup.setData(0);
		boolean b3 = lookup.checkFields();
		assertFalse(b3, "Lookup value should be bigger than zero");
	}

	@Test
	@Rollback(false)
	@Order(3)
	/**
	 * This test tries to create an instance of the Lookup object in the mem db
	 */
	void testCreateLookup() {
		this.setUp();// create
		this.lookupRepository.save(this.validLookup);// add
		assertNotNull(validLookup);// check
	}

	@Test
	@Rollback(false)
	@Order(4)
	/**
	 * This test tries to find an instance of the Lookup object in the DB by value
	 * 
	 */
	void testFindLookupByValue() {
		int value = 1;// create
		int category = 0;// create
		Optional<Lookup> oLookup = this.lookupRepository.findByDataAndCategory(value, category);// find
		Lookup lookup = oLookup.orElse(null);
		assertNotNull(lookup);
		assertThat(lookup.getData()).isEqualTo(value);// check
	}

	@Test
	@Rollback(true)
	@Order(5)
	/**
	 * This test tries to fail finding an instance of the Lookup object in the mem
	 * db by value
	 */
	void testFindLookupByValueNotExists() {
		int value = 2;// create
		int category = 1;// create
		Optional<Lookup> oLookup = this.lookupRepository.findByDataAndCategory(value, category);// find
		Lookup lookup = oLookup.orElse(null);
		assertNull(lookup);// check
	}

	@Test
	@Rollback(true)
	@Order(6)
	/**
	 * This test tries to update an instance of the Lookup object in the mem db
	 */
	void testUpdateLookup() {
		int value = 1;// create
		int category = 0;// create
		String newName = "Affiliate2";
		Optional<Lookup> oLookup = this.lookupRepository.findByDataAndCategory(value, category);// find
		Lookup lookup = oLookup.orElse(null);
		assertNotNull(lookup);
		lookup.setName(newName);
		Lookup savedLookup = this.lookupRepository.save(lookup);// add
		assertThat(savedLookup.getName()).isEqualTo(newName);// check
	}

	@Test
	@Rollback(true)
	@Order(7)
	/**
	 * This test tries to the instance of the Lookup object in the mem db
	 */
	void testListLookup() {
		Iterable<Lookup> memIterable = this.lookupRepository.findAll();
		assertNotNull(memIterable);
		assertThat(memIterable).size().isPositive();
	}

	@Test
	@Rollback(true)
	@Order(8)
	/**
	 * This test tries delete an instance of the Lookup object in the mem db
	 */
	void testDeleteLookup() {
		int value = 1;// create;
		int category = 0;// create
		Optional<Lookup> oLookup = this.lookupRepository.findByDataAndCategory(value, category);// find
		Lookup lookup = oLookup.orElse(null);
		assertNotNull(lookup);
		this.lookupRepository.delete(lookup);
		boolean deleted = this.lookupRepository.findById(lookup.getId()).isPresent();
		assertFalse(deleted);
	}

}
