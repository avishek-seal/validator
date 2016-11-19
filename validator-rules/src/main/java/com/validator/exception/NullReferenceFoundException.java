package com.validator.exception;

public class NullReferenceFoundException extends Exception {

	private static final long serialVersionUID = 1631480365717328933L;
	
	public NullReferenceFoundException() {
		super("No value is passed");
	}
}
