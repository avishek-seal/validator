package com.validator.exception;

public class InvalidTextLengthException extends Exception {

	private static final long serialVersionUID = -956327997685620366L;
	
	public InvalidTextLengthException(int max, int min, int present, String field) {
		super("Invalid " + field + " length, valid lngth between #min and #max present #present".replace("#max", String.valueOf(max)).replace("#min", String.valueOf(min)).replace("#present", String.valueOf(present)));
	}
}
