package com.store.discounts.jpa.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.store.discounts.jpa.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

	Optional<Customer> findByName(String name);

}
