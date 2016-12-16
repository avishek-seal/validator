package com.validator.spec;

/**
 * this Interface defines the protocol need to implement for validation
 * 
 * @author Avishek Seal
 * @since Dec 16, 2016
 */
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
