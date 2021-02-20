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

import com.store.discounts.jpa.model.ItemType;
import com.store.discounts.jpa.repository.ItemTypeRepository;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class ItemTypeTest {
	// ======================================
	// = Attributes =
	// ======================================
	ItemType validItemType;
	@Autowired
	ItemTypeRepository itemTypeRepository;

	public void setUp() {
		// Creates a valid customer
		validItemType = new ItemType();
		validItemType.setId(1);
		validItemType.setName("groceries");
		validItemType.setValue(0);
	}

	@Test
	@Rollback(true)
	@Order(1)
	/**
	 * This test tries to create an Item with valid values.
	 */
	void testCreateValidItemType() {
		this.setUp();
		assertEquals("groceries", validItemType.getName());
		assertEquals(1, validItemType.getId());
		assertEquals(0, validItemType.getValue());
		boolean okay = validItemType.checkFields();
		assertTrue(okay, "ItemType data is OK!");
	}

	@Test
	@Rollback(true)
	@Order(2)
	/**
	 * This test tries to create Item with invalid values.
	 */
	void testCreateInvalidItemType() {
		// Creates objects with empty values
		ItemType itemType = new ItemType();
		itemType.setId(0);
		boolean b = itemType.checkFields();
		assertFalse(b, "ItemType id should be bigger than zero!");
		itemType.setName(
				"Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcmAwad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2");
		boolean b2 = itemType.checkFields();
		assertFalse(b2, "ItemType name should be smaller than 256!");
		itemType.setValue(-1);
		boolean b3 = itemType.checkFields();
		assertFalse(b3, "ItemType value should be bigger than zero");
	}

	@Test
	@Rollback(false)
	@Order(3)
	/**
	 * This test tries to create an instance of the ItemType object in the mem db
	 */
	void testCreateItemType() {
		this.setUp();// create
		this.itemTypeRepository.save(this.validItemType);// add
		assertNotNull(validItemType);// check
	}

	@Test
	@Rollback(false)
	@Order(4)
	/**
	 * This test tries to find an instance of the ItemType object in the DB by value
	 * 
	 */
	void testFindItemTypeByName() {
		String name = "groceries";
		Optional<ItemType> oItemType = this.itemTypeRepository.findByName(name);// find
		ItemType itemType = oItemType.orElse(null);
		assertNotNull(itemType);
		assertThat(itemType.getName()).isEqualTo(name);// check
	}

	@Test
	@Rollback(true)
	@Order(5)
	/**
	 * This test tries to fail finding an instance of the ItemType object in the mem
	 * db by value
	 */
	void testFindItemTypeByNameNotExists() {
		String name = "Chips";
		Optional<ItemType> oItemType = this.itemTypeRepository.findByName(name);// find
		ItemType itemType = oItemType.orElse(null);
		assertNull(itemType);// check
	}

	@Test
	@Rollback(true)
	@Order(6)
	/**
	 * This test tries to update an instance of the ItemType object in the mem db
	 */
	void testUpdateItemType() {
		String name = "groceries";
		String newName = "Chips";
		Optional<ItemType> oItemType = this.itemTypeRepository.findByName(name);// find
		ItemType itemType = oItemType.orElse(null);
		assertNotNull(itemType);
		itemType.setName(newName);
		ItemType savedItemType = this.itemTypeRepository.save(itemType);// add
		assertThat(savedItemType.getName()).isEqualTo(newName);// check
	}

	@Test
	@Rollback(true)
	@Order(7)
	/**
	 * This test tries to the instance of the ItemType object in the mem db
	 */
	void testListItemType() {
		Iterable<ItemType> memIterable = this.itemTypeRepository.findAll();
		assertNotNull(memIterable);
		assertThat(memIterable).size().isPositive();
	}

	@Test
	@Rollback(true)
	@Order(8)
	/**
	 * This test tries delete an instance of the ItemType object in the mem db
	 */
	void testDeleteItemType() {
		String name = "groceries";
		Optional<ItemType> oItemType = this.itemTypeRepository.findByName(name);// find
		ItemType itemType = oItemType.orElse(null);
		assertNotNull(itemType);
		this.itemTypeRepository.delete(itemType);
		boolean deleted = this.itemTypeRepository.findById(itemType.getId()).isPresent();
		assertFalse(deleted);
	}

}
