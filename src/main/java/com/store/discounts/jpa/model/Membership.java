package com.store.discounts.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.store.discounts.utils.Constants;
import com.store.discounts.utils.StringUtils;

@Entity
@Table(name = "membership")
public class Membership {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "priority")
	private int priority;

	@Column(name = "discount")
	private int discount;

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	@Column(name = "name", length = 256)
	private String name;

	@OneToOne(mappedBy = "membership")
	private Customer customer;

	public Membership() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean checkFields() {
		return !StringUtils.isEmptyOrNull(this.name) && this.name.length() <= 256 && this.id > 0 && this.priority >= 0
				&& this.priority <= 3
				&& (this.discount == Constants.EMPLOYEE_DISCOUNT || this.discount == Constants.AFFILIATE_DISCOUNT
						|| this.discount == Constants.LOYAL_CUSTOMER_DISCOUNT
						|| this.discount == Constants.REGULAR_CUSTOMER_DISCOUNT);
	}

}
