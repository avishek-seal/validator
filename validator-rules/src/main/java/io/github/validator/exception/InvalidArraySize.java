package io.github.validator.exception;

public class InvalidArraySize extends Exception {

	private static final long serialVersionUID = 7427158977396640103L;

	public InvalidArraySize(int originalLength, int maxLength, int minLength, String field) {
		super("Invalid size of " + field + " #length, size must be between #minLength & #maxLength".replace("#length", String.valueOf(originalLength)).replace("#maxLength", String.valueOf(maxLength)).replace("#minLength", String.valueOf(minLength)));
	}
}
