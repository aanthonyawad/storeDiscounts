package com.store.discounts.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.store.discounts.utils.StringUtils;

@Entity
@Table(name = "lookup")
public class Lookup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "data")
	private int data;

	@Column(name = "category")
	private int category;

	@Column(name = "name", length = 256)
	private String name;

	public Lookup() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public boolean checkFields() {
		return !StringUtils.isEmptyOrNull(this.name) && this.name.length() <= 256 && this.id > 0 && this.data > -1
				&& this.category > -1;
	}

}
