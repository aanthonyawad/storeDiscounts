package com.store.discounts.utils;

public class Constants {

	// ======================================
	// = DISCOUNTS =
	// ======================================
	public static final int CATEGORY_DISCOUNTS = 0;
	
	
	public static final String EMPLOYEE_STRING = "employee";
	public static final String AFFILIATE_STRING = "affiliate";
	public static final String LOYAL_CUSTOMER_STRING = "loyal customer";
	public static final String REGULAR_CUSTOMER_STRING = "regular customer";

	public static final int EMPLOYEE_VALUE = 0;
	public static final int AFFILIATE_VALUE = 1;
	public static final int LOYAL_CUSTOMER_VALUE = 2;
	public static final int REGULAR_CUSTOMER_VALUE= 3;
	
	
	public static final int EMPLOYEE_DISCOUNT = 30;//PERCENT OF THE WHOLE BILL
	public static final int AFFILIATE_DISCOUNT= 10;//PERCENT OF THE WHOLE BILL
	public static final int LOYAL_CUSTOMER_DISCOUNT = 5;//PERCENT OF THE WHOLE BILL
	public static final int REGULAR_CUSTOMER_DISCOUNT= 0;//every 100$ he gets 5$ discount 200$ becomes 190 applies on groceries type
	
	
	

	// ======================================
	// = ITEM TYPES =
	// ======================================
	public static final int CATEGORY_ITEM_TYPES = 1;
	
	public static final String ITEM_TYPE_GROCERIES = "groceries";
	public static final String ITEM_TYPE_OTHER = "other";

	public static final int GROCERIES_VALUE = 0;
	public static final int OTHER_VALUE = 1;
	
	
}
