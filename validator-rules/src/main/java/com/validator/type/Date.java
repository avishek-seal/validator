package com.validator.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.validator.constant.DatePattern;

/**
 * this validator is used to validate date
 * provided a date/ date time pattern
 * 
 * @author Avishek Seal
 * @since Dec 16, 2016
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Date{
	
	/**
	 * this option is used to set the date/date time pettern
	 * @return
	 */
	DatePattern pattern() default DatePattern.DATE_HH_MM_SS;
	
	/**
	 * this option is used to set the visible field name of the property
	 * @return
	 */
	String fieldName();
}
