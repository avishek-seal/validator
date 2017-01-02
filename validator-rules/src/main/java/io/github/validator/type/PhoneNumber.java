package io.github.validator.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * this validator is used to validate phone number
 * provided is country code is available in the text or not
 * and length of the mobile number
 * 
 * @author Avishek Seal
 * @since Dec 16, 2016
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PhoneNumber {
	
	/**
	 * this options is used to set whether
	 * country code will be available in phone number or not
	 * @return
	 */
	boolean countryCode() default false;
	
	/**
	 * this option is used to set the length of the phone number must be
	 * @return
	 */
	int length() default 10;
	
	/**
	 * this option is used to set the visible field name of the property
	 * @return
	 */
	String fieldName();
}
