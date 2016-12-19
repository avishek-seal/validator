package com.validator.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * this annotation is used to validate Array of elements
 * 
 * @author Avishek Seal
 * @since Dec 19, 2016
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Collection {
	
	/**
	 * this option is used to validate the uniqueness of elements
	 * 
	 * @return
	 */
	boolean uniqueValue() default false;
	
	/**
	 * this option is used to validate whether the length of the array will be
	 * fixed or not.
	 * 
	 * @return
	 */
	int minElements() default Integer.MIN_VALUE;
	
	/**
	 * this option is used to check, when the length is fixed whether
	 * the array length is valid or not.
	 * 
	 * @return
	 */
	int maxElements() default Integer.MAX_VALUE;
	
	/**
	 * this option is used to set the visible field name of the property
	 * @return
	 */
	String fieldName();
}
