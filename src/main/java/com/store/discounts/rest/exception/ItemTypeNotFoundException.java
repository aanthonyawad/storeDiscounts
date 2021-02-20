package com.store.discounts.rest.exception;

public class ItemTypeNotFoundException extends Exception {
	private final String s;
	public ItemTypeNotFoundException(String s) {
		this.s = s;
	}

	@Override
	public String toString() {
		return s;
	}
}
