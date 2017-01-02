package io.github.validator.exception;

public class DuplicateValueException extends Exception {

	private static final long serialVersionUID = 6581343022952333010L;

	public DuplicateValueException(String field, String value) {
		super("Duplicate value " + value + " in " + field);
	}
}
