package io.github.validator.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.github.validator.constant.ContentType;

/**
 * this validator is used to validate if a property is holding
 * any text value then that text value matches the
 * provided length barier and content type
 * 
 * @author Avishek Seal
 * @since Dec 16, 2016
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OptionalText {

	/**
	 * this option is used to set the min length of the text
	 * @return
	 */
	int minLength() default 0;
	
	/**
	 * this option is used to set max length of the text
	 * @return
	 */
	int maxLength() default 256;
	
	/**
	 * this option is used to set the content type of the text
	 * @return
	 */
	ContentType contentType() default ContentType.ALL;
	
	/**
	 * this option is used to set the visible field name of the property
	 * @return
	 */
	String fieldName();
}
