package com.store.discounts.jpa.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.store.discounts.jpa.model.ItemType;

public interface ItemTypeRepository extends CrudRepository<ItemType, Long>{

	Optional<ItemType> findByName(String name);

}
