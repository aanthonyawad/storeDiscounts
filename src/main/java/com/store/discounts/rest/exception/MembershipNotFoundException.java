package com.store.discounts.rest.exception;

public class MembershipNotFoundException extends Exception {
	private final String s;
	public MembershipNotFoundException(String s) {
		this.s = s;
	}

	@Override
	public String toString() {
		return s;
	}
}
