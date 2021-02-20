package com.store.discounts.utils;

public class StringUtils {
	private StringUtils() {
		
	}
	public static boolean isEmptyOrNull(String s) {
		if (s != null)
			return s.isEmpty();
		return true;
	}
}
