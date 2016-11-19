package com.validator.exception;

public class InvalidPhoneNumberException extends Exception {

	private static final long serialVersionUID = -5269666281930075798L;
	
	public InvalidPhoneNumberException() {
		super("Not a valid phone number");
	}
}
