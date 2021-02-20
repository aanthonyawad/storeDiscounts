package com.store.discounts.rest.exception;

public class MembershipNotFoundException extends Exception {
	private String s;
	public MembershipNotFoundException(String s) {
		this.s = s;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return s;
	}
}
