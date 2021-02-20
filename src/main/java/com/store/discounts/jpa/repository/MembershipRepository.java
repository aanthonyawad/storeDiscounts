package com.store.discounts.jpa.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.store.discounts.jpa.model.Membership;

public interface MembershipRepository extends CrudRepository<Membership, Long>{

	Optional<Membership> findByPriority(int priority);

}
