package com.store.discounts.rest.exception;

public class ItemTypeNotFoundException extends Exception {
	private String s;
	public ItemTypeNotFoundException(String s) {
		this.s = s;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return s;
	}
}
