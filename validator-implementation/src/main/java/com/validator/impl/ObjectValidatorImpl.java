package com.validator.impl;

import com.validator.spec.AbstractRegisterValidator;
import com.validator.spec.ObjectValidator;
/**
 * this class is used to modify the Validation Specification
 * 
 * @author Avishek Seal
 * @since Dec 16, 2016
 */
public class ObjectValidatorImpl extends AbstractRegisterValidator implements ObjectValidator{

	@Override
	public <T> void validate(T t) throws Exception {
		modelValidate(t);
	}
}
