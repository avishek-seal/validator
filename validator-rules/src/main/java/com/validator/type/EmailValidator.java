package com.validator.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.validator.constant.EmailPattern;

/**
 * this validator is used to validate an email address
 * provided the common pattern now present.
 * 
 * @author Avishek Seal
 * @since Dec 16, 2016
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EmailValidator{
	
	/**
	 * this option is used to set the pattern of the email address
	 * @return
	 */
	EmailPattern pattern() default EmailPattern.COMMON_PATTERN;
	
	/**
	 * this option is used to set the visible field name of the property
	 * @return
	 */
	String fieldName();
}
