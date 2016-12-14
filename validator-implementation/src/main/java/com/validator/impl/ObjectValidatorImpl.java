package com.validator.impl;

import com.validator.spec.AbstractRegisterValidator;
import com.validator.spec.ObjectValidator;

public class ObjectValidatorImpl extends AbstractRegisterValidator implements ObjectValidator{

	@Override
	public <T> void validate(T t) throws Exception {
		modelValidate(t);
	}
}
