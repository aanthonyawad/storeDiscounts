package com.store.discounts.jpa.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.store.discounts.rest.request.CustomerRequest;
import com.store.discounts.utils.LookupUtils;
import com.store.discounts.utils.StringUtils;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "name", length = 256)
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "membership", referencedColumnName = "id")
	private Membership membership;
	


	@OneToMany(fetch = FetchType.LAZY,mappedBy="customer")
	private Set<CustomerBillHistory> customerBillHistory;

	public Customer() {
		super();
	}

	public Customer(CustomerRequest customer) {
		this.id = customer.getId();
		this.name = customer.getName();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public boolean checkFields() {
		return this.id > 0 && !StringUtils.isEmptyOrNull(this.name) && this.name.length() <= 256;
	}

	public void setMembership(Lookup lookup) {
		this.membership = new Membership();
		this.membership.setCustomer(this);
		this.membership.setPriority(lookup.getData());
		this.membership.setDiscount(LookupUtils.pickDiscount(this.membership.getPriority()));
		this.membership.setName(lookup.getName());
		
	}

	public Set<CustomerBillHistory> getCustomerBillHistory() {
		return customerBillHistory;
	}

	public void setCustomerBillHistory(Set<CustomerBillHistory> customerBillHistory) {
		this.customerBillHistory = customerBillHistory;
	}

}
