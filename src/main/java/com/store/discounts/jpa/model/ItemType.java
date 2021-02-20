package com.store.discounts.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.store.discounts.utils.StringUtils;

@Entity
@Table(name = "itemType")
public class ItemType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "value")
	private int value;

	@Column(name = "name", length = 256)
	private String name;

	@OneToOne(mappedBy = "itemType")
	private Item item;

	public ItemType() {
		super();
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean checkFields() {
		return this.id > 0 && !StringUtils.isEmptyOrNull(this.name) && this.name.length() <= 256
				&& value >= 0;
	}

}
