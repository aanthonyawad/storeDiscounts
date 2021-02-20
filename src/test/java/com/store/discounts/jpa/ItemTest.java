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

import com.store.discounts.jpa.model.Item;
import com.store.discounts.jpa.repository.ItemRepository;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class ItemTest {
	// ======================================
	// = Attributes =
	// ======================================
	Item validItem;
	@Autowired
	ItemRepository itemRepository;

	public void setUp() {
		// Creates a valid customer
		validItem = new Item();
		validItem.setId(1);
		validItem.setPrice(20);
		validItem.setName("Cheese");

	}

	@Test
	@Rollback(true)
	@Order(1)
	/**
	 * This test tries to create an Item with valid values.
	 */
	public void testCreateValidItem() {
		this.setUp();
		assertEquals("Cheese", validItem.getName());
		assertEquals(1, validItem.getId());
		assertEquals(20, validItem.getPrice());
		boolean okay = validItem.checkFields();
		assertTrue(okay, "ItemType data is OK!");
	}

	@Test
	@Rollback(true)
	@Order(2)
	/**
	 * This test tries to create Item with invalid values.
	 */
	public void testCreateInvalidItem() {
		// Creates objects with empty values
		Item item = new Item();
		item.setId(0);
		boolean b = item.checkFields();
		assertFalse(b, "Item id should be bigger than zero!");
		item.setName(
				"Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcmAwad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2");
		boolean b2 = item.checkFields();
		assertFalse(b2, "Item name should be smaller than 256!");
		item.setPrice(-1);
		boolean b3 = item.checkFields();
		assertFalse(b3, "Item name price should be bigger than 0!");
	}

	@Test
	@Rollback(false)
	@Order(3)
	/**
	 * This test tries to create an instance of the Item object in the mem db
	 */
	public void testCreateItem() {
		this.setUp();// create
		this.itemRepository.save(this.validItem);// add
		assertNotNull(validItem);// check
	}

	@Test
	@Rollback(false)
	@Order(4)
	/**
	 * This test tries to find an instance of the Item object in the DB by value
	 * 
	 */
	public void testFindItemByName() {
		String name = "Cheese";
		Optional<Item> oItem = this.itemRepository.findByName(name);// find
		Item item = oItem.orElse(null);
		assertNotNull(item);
		assertThat(item.getName()).isEqualTo(name);// check
	}

	@Test
	@Rollback(true)
	@Order(5)
	/**
	 * This test tries to fail finding an instance of the Item object in the mem
	 * db by value
	 */
	public void testFindItemByNameNotExists() {
		String name = "Chips";
		Optional<Item> oItem = this.itemRepository.findByName(name);// find
		Item item = oItem.orElse(null);
		assertNull(item);// check
	}

	@Test
	@Rollback(true)
	@Order(6)
	/**
	 * This test tries to update an instance of the Item object in the mem db
	 */
	public void testUpdateItem() {
		String name = "Cheese";
		String newName = "Chips";
		Optional<Item> oItem = this.itemRepository.findByName(name);// find
		Item item = oItem.orElse(null);
		assertNotNull(item);
		item.setName(newName);
		Item savedType = this.itemRepository.save(item);// add
		assertThat(savedType.getName()).isEqualTo(newName);// check
	}

	@Test
	@Rollback(true)
	@Order(7)
	/**
	 * This test tries to the instance of the Item object in the mem db
	 */
	public void testListItem() {
		Iterable<Item> memIterable = this.itemRepository.findAll();
		assertNotNull(memIterable);
		assertThat(memIterable).size().isGreaterThan(0);
	}

	@Test
	@Rollback(true)
	@Order(8)
	/**
	 * This test tries delete an instance of the Item object in the mem db
	 */
	public void testDeleteItem() {
		String name = "Cheese";
		Optional<Item> oItem = this.itemRepository.findByName(name);// find
		Item item = oItem.orElse(null);
		assertNotNull(item);
		this.itemRepository.delete(item);
		boolean deleted = this.itemRepository.findById(item.getId()).isPresent();
		assertFalse(deleted);
	}

}
