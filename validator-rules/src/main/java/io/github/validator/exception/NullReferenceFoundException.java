package io.github.validator.exception;

public class NullReferenceFoundException extends Exception {

	private static final long serialVersionUID = 1631480365717328933L;
	
	public NullReferenceFoundException(String field) {
		super("No value for " + field.trim());
	}
}
