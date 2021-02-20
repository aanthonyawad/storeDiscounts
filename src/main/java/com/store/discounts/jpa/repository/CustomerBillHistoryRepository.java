package com.store.discounts.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.store.discounts.jpa.model.CustomerBillHistory;

public interface CustomerBillHistoryRepository extends CrudRepository<CustomerBillHistory, Long>{

}
