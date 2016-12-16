package com.validator.exception;

public class InvalidDateException extends Exception{

	private static final long serialVersionUID = -6032427109027329483L;

	public InvalidDateException(String field) {
		super("Invalid " + field.trim());
	}
}
