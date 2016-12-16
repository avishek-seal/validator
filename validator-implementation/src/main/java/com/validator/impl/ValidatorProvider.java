package com.validator.impl;

import com.validator.spec.ObjectValidator;

/**
 * this class is used to get singleton instance of
 * ObjectValidationImpl class
 *  
 * @author Avishek Seal
 * @since Dec 16, 2016
 */
public class ValidatorProvider {
	
	private static final ObjectValidatorImpl OBJECT_VALIDATOR_IMPL = new ObjectValidatorImpl();
	
	/**
	 * this method returns the singleton instance
	 * 
	 * @author Avishek Seal
	 * @since Dec 16, 2016
	 * @return ObjectValidator
	 */
	public static final ObjectValidator getInstance() {
		return OBJECT_VALIDATOR_IMPL;
	}
}
