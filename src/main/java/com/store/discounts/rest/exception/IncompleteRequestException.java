package com.store.discounts.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class IncompleteRequestException extends RuntimeException {
	private final String s;
	public IncompleteRequestException(String s) {
		this.s = s;
	}

	@Override
	public String toString() {
		return s;
	}
}
