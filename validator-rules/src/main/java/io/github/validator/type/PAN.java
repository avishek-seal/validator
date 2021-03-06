package io.github.validator.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.github.validator.constant.PANPattern;

/**
 * this validator is used to check whether
 * PAN is validate or not provided
 * country wise PAN Pattern
 * 
 * @author Avishek Seal
 * @since Dec 16, 2016
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PAN {
	
	/**
	 * this option is used to set country wise PAN pattern
	 * @return
	 */
	PANPattern pattern() default PANPattern.INDIAN;
	
	/**
	 * this option is used to set the visible field name of the property
	 * @return
	 */
	String fieldName();
}
