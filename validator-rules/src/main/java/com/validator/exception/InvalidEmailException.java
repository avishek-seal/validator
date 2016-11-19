package com.validator.exception;

public class InvalidEmailException extends Exception{
	
	private static final long serialVersionUID = 365473552099408849L;

	public InvalidEmailException() {
		super("Not a valid email id");
	}
}
