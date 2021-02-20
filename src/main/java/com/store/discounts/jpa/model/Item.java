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

import com.store.discounts.rest.exception.IncompleteRequestException;
import com.store.discounts.rest.request.ItemRequest;
import com.store.discounts.utils.StringUtils;

@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "name", length = 256)
	private String name;

	@Column(name = "price")
	private double price;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "item_type", referencedColumnName = "id")
	private ItemType itemType;
	


	@OneToMany(fetch = FetchType.LAZY,mappedBy="item")
	private Set<CustomerBillHistory> customerBillHistory;

	public Item() {
		super();
	}

	public Item(ItemRequest item) throws IncompleteRequestException {
		this.name = item.getName();
		this.price = item.getPrice();
		if(!this.checkRequestFields()) throw new IncompleteRequestException("Incomple item sent !!");
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

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean checkFields() {
		return this.id > 0 && !StringUtils.isEmptyOrNull(this.name) && this.name.length() <= 256
				&& this.price > 0;
	}
	public boolean checkRequestFields() {
		return !StringUtils.isEmptyOrNull(this.name) && this.name.length() <= 256
				&& this.price > 0;
	}

	public void setItemType(Lookup lookup) {
		this.itemType = new ItemType();
		itemType.setItem(this);
		itemType.setName(lookup.getName());
		itemType.setValue(lookup.getData());
	}

	public Set<CustomerBillHistory> getCustomerBillHistory() {
		return customerBillHistory;
	}

	public void setCustomerBillHistory(Set<CustomerBillHistory> customerBillHistory) {
		this.customerBillHistory = customerBillHistory;
	}

}
