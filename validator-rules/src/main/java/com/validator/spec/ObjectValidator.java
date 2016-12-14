package com.validator.spec;

@FunctionalInterface
public interface ObjectValidator {

	/**
	 * this method is used to validate object of any class having
	 * annotation of {com.validator.type} package over the properties
	 * of that class.
	 * 
	 * @author Avishek Seal
	 * @since 14-Dec-2016
	 * @param t
	 * @throws Exception
	 */
	<T> void validate(T t) throws Exception;
}
