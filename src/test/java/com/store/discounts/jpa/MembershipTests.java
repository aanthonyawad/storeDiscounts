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

import com.store.discounts.jpa.model.Membership;
import com.store.discounts.jpa.repository.MembershipRepository;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
 class MembershipTests {
	// ======================================
	// = Attributes =
	// ======================================
	Membership validMembership;
	@Autowired
	MembershipRepository membershipRepository;

	 void setUp() {
		// Creates a valid customer
		validMembership = new Membership();
		validMembership.setId(1);
		validMembership.setName("Affiliate");
		validMembership.setPriority(1);
		validMembership.setDiscount(10);
	}

	@Test
	@Rollback(true)
	@Order(1)
	/**
	 * This test tries to create an Membership with valid values.
	 */
	 void testCreateValidMembership() {
		this.setUp();
		assertEquals(1, validMembership.getId());
		assertEquals(1, validMembership.getPriority());
		assertEquals(10, validMembership.getDiscount());
		assertEquals("Affiliate", validMembership.getName());
		boolean okay = validMembership.checkFields();
		assertTrue(okay, "Membership data is OK!");
	}

	@Test
	@Rollback(true)
	@Order(2)
	/**
	 * This test tries to create Membership with invalid values.
	 */
	 void testCreateInvalidMembership() {
		// Creates objects with empty values
		Membership membership = new Membership();
		membership.setId(0);
		boolean b = membership.checkFields();
		assertFalse(b, "Customer id should be bigger than zero!");
		Membership membership1 = new Membership();
		membership1.setName(
				"Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcmAwad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2Awad35329847329537391273912312jehfdksdjdbskfbakafbsadkfbasdqey29y2193412y39812yeakfbzdcszbcm,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2,dsbfcddmfbamdfasbdksabdaskdbaskdasbwqie2y3i2");
		boolean b2 = membership.checkFields();
		assertFalse(b2, "Membership name should be bigger smaller than 256!");
		membership.setPriority(4);
		boolean b3 = membership.checkFields();
		assertFalse(b3, "Membership priority should be between zero and three!");
		membership.setDiscount(45);
		boolean b4 = membership.checkFields();
		assertFalse(b4, "Membership discount should be one of the following 30, 10, 5 or none base on the priority!");
	}

	@Test
	@Rollback(false)
	@Order(3)
	/**
	 * This test tries to create an instance of the Membership object in the mem db
	 */
	 void testCreateMembership() {
		this.setUp();// create
		this.membershipRepository.save(this.validMembership);// add
		assertNotNull(validMembership);// check
	}

	@Test
	@Rollback(false)
	@Order(4)
	/**
	 * This test tries to find an instance of the Membership object in the DB by the
	 * user priority
	 */
	 void testFindMembershipByPriority() {
		int priority = 1;// create
		Optional<Membership> oMembership = this.membershipRepository.findByPriority(priority);// find
		Membership membership = oMembership.orElse(null);
		assertNotNull(membership);
		assertThat(membership.getPriority()).isEqualTo(priority);// check
	}

	@Test
	@Rollback(true)
	@Order(5)
	/**
	 * This test tries to fail find finding an instance of the Membership object in
	 * the DB by the user priority
	 */
	 void testFindMembershipByPriorityNotExists() {
		int priority = 2;// create
		Optional<Membership> oMembership = this.membershipRepository.findByPriority(priority);// find
		Membership membership = oMembership.orElse(null);
		assertNull(membership);// check
	}

	@Test
	@Rollback(true)
	@Order(6)
	/**
	 * This test tries update an instance of the Membership object in the DB by the
	 * user priority
	 */
	 void testUpdateMembership() {
		int priority = 1;// create
		String newName = "Affiliate2";
		Optional<Membership> oMembership = this.membershipRepository.findByPriority(priority);// find
		Membership membership = oMembership.orElse(null);
		assertNotNull(membership);
		membership.setName(newName);
		Membership savedmembership = this.membershipRepository.save(membership);// add
		assertThat(savedmembership.getName()).isEqualTo(newName);// check
	}

	@Test
	@Rollback(true)
	@Order(7)
	/**
	 * This test tries list instances of the Membership object in the DB
	 */
	 void testListMembership() {
		Iterable<Membership> memIterable = this.membershipRepository.findAll();
		assertNotNull(memIterable);
		assertThat(memIterable).size().isPositive();
	}

	@Test
	@Rollback(true)
	@Order(8)
	/**
	 * This test tries to delete an instance of the Membership object in the DB
	 */
	 void testDeleteMembership() {
		int priority = 1;// create;
		Optional<Membership> oMembership = this.membershipRepository.findByPriority(priority);// find
		Membership membership = oMembership.orElse(null);
		assertNotNull(membership);
		this.membershipRepository.delete(membership);
		boolean deleted = this.membershipRepository.findById(membership.getId()).isPresent();
		assertFalse(deleted);
	}

}
