package com.validator.exception;

public class InvalidTextFormatException extends Exception {
	
	private static final long serialVersionUID = 5747504538658098334L;
	
	public InvalidTextFormatException() {
		super("Not a valid text format");
	}
}
