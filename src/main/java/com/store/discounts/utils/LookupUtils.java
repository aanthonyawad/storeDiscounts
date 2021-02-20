package com.store.discounts.utils;

public class LookupUtils {
	private LookupUtils() {
		
	}
	public static int pickDiscount(int priority) {
		switch (priority) {
			case Constants.EMPLOYEE_VALUE :return Constants.EMPLOYEE_DISCOUNT;
			case Constants.AFFILIATE_VALUE :return Constants.AFFILIATE_DISCOUNT;
			case Constants.LOYAL_CUSTOMER_VALUE :return Constants.LOYAL_CUSTOMER_DISCOUNT;
			case Constants.REGULAR_CUSTOMER_VALUE :return Constants.REGULAR_CUSTOMER_DISCOUNT;
			default : return Constants.REGULAR_CUSTOMER_DISCOUNT;
		}
		
	}

}
