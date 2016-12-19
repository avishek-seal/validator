package com.validator.exception;

public class NotAnArrayException extends Exception{

	private static final long serialVersionUID = -4708473999144706779L;

	public NotAnArrayException(String field) {
		super(field + " are not an array");
	}
}
