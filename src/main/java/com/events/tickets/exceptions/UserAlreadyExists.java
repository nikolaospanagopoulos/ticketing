package com.events.tickets.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserAlreadyExists extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String field;
	private String value;

	public UserAlreadyExists(String field, String value) {
		super(String.format("User with %s : '%s' already exists", field, value));
		this.field = field;
		this.value = value;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
