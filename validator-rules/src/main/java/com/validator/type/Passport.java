package com.validator.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.validator.constant.PassportPattern;

/**
 * this validator is used to validate the passport
 * provided the Country wise pattern
 * 
 * @author Avishek Seal
 * @since Dec 16, 2016
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Passport {

	/**
	 * this option is used to set country specific passport pattern
	 * @return
	 */
	PassportPattern pattern() default PassportPattern.INDIAN;
	
	/**
	 * this option is used to set the visible field name of the property
	 * @return
	 */
	String fieldName();
}
