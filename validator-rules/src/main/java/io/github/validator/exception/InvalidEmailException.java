package io.github.validator.exception;

public class InvalidEmailException extends Exception{
	
	private static final long serialVersionUID = 365473552099408849L;

	public InvalidEmailException(String field) {
		super("Invalid " + field.trim());
	}
}
