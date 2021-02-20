package com.store.discounts.jpa.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.store.discounts.jpa.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{

	Optional<Item> findByName(String name);

}
