package com.store.discounts;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.store.discounts.jpa.model.Lookup;
import com.store.discounts.jpa.repository.LookupRepository;
import com.store.discounts.utils.Constants;

@SpringBootApplication
public class Application {
	@Autowired
	LookupRepository lookupRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// fill database with static values
	@PostConstruct
	private void addStaticValues() {

		// ======================================
		// = DISCOUNTS =
		// ======================================
		Lookup l0 = this.generateLookup(Constants.EMPLOYEE_STRING, Constants.EMPLOYEE_VALUE,
				Constants.CATEGORY_DISCOUNTS);
		Lookup l1 = this.generateLookup(Constants.AFFILIATE_STRING, Constants.AFFILIATE_VALUE,
				Constants.CATEGORY_DISCOUNTS);
		Lookup l2 = this.generateLookup(Constants.LOYAL_CUSTOMER_STRING, Constants.LOYAL_CUSTOMER_VALUE,
				Constants.CATEGORY_DISCOUNTS);
		Lookup l3 = this.generateLookup(Constants.REGULAR_CUSTOMER_STRING, Constants.REGULAR_CUSTOMER_VALUE,
				Constants.CATEGORY_DISCOUNTS);

		lookupRepository.save(l0);
		lookupRepository.save(l1);
		lookupRepository.save(l2);
		lookupRepository.save(l3);

		// ======================================
		// = ITEM TYPES =
		// ======================================
		Lookup l4 = this.generateLookup(Constants.ITEM_TYPE_GROCERIES, Constants.GROCERIES_VALUE,
				Constants.CATEGORY_ITEM_TYPES);
		Lookup l5 = this.generateLookup(Constants.ITEM_TYPE_OTHER, Constants.OTHER_VALUE,
				Constants.CATEGORY_ITEM_TYPES);		

		lookupRepository.save(l4);
		lookupRepository.save(l5);
		
	}

	private Lookup generateLookup(String name, int data, int category) {
		Lookup lookup = new Lookup();
		lookup.setName(name);
		lookup.setData(data);
		lookup.setCategory(category);
		return lookup;
	}
}
