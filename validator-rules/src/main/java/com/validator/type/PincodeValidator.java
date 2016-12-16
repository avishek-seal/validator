package com.validator.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * this validator is used to validate the pin number
 * @author Avishek Seal
 * @since Dec 16, 2016
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PincodeValidator {
	
	/**
	 * this option is used to set the visible field name of the property
	 * @return
	 */
	String fieldName();
}
