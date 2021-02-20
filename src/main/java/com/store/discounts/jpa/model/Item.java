package com.store.discounts.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

	public Item() {
		super();
	}

	public Item(ItemRequest item) {
		this.name = item.getName();
		this.price = item.getPrice();
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
		// TODO Auto-generated method stub
		return this.id > 0 && !StringUtils.isEmptyOrNull(this.name) && this.name.length() <= 256
				&& this.price > 0;
	}

	public void setItemType(Lookup lookup) {
		this.itemType = new ItemType();
		itemType.setItem(this);
		itemType.setName(lookup.getName());
		itemType.setValue(lookup.getData());
	}

}
