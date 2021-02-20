package com.store.discounts.jpa.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.store.discounts.jpa.model.Lookup;

public interface LookupRepository extends CrudRepository<Lookup, Long>{

	Optional<Lookup> findByDataAndCategory(int data, int category);

}
