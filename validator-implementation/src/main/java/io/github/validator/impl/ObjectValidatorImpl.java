package io.github.validator.impl;

import io.github.validator.spec.AbstractRegisterValidator;
import io.github.validator.spec.ObjectValidator;
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
