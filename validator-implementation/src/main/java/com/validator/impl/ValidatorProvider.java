package com.validator.impl;

public class ValidatorProvider {
	
	private static final ObjectValidatorImpl OBJECT_VALIDATOR_IMPL = new ObjectValidatorImpl();
	
	public static final ObjectValidatorImpl getInstance() {
		return OBJECT_VALIDATOR_IMPL;
	}
}
