package com.validator.exception;

public class InvalidTextLengthException extends Exception {

	private static final long serialVersionUID = -956327997685620366L;
	
	public InvalidTextLengthException(int valid, int present) {
		super("Invalid text length, valid #valid present #present".replace("#valid", String.valueOf(valid)).replace("#present", String.valueOf(present)));
	}
}