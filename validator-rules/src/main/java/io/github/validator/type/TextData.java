package io.github.validator.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.github.validator.constant.ContentType;

/**
 * this validator is used to validate if a property is holding
 * text value and no null, then that text value matches the
 * provided length barier and content type
 * 
 * @author Avishek Seal
 * @since Dec 16, 2016
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TextData {
	
	/**
	 * this option is used to set min length of a text data
	 * @return
	 */
	int minLength() default 0;
	
	/**
	 * this option is used to set max length of a text data
	 * @return
	 */
	int maxLength() default 256;
	/**
	 * this option is used to set whether length check is needed or not
	 * @return
	 */
	boolean lengthCheck() default true;
	
	/**
	 * this option is used to set content type of the text data
	 * @return
	 */
	ContentType contentType() default ContentType.ALL;
	
	/**
	 * this option is used to set the visible field name of the property
	 * @return
	 */
	String fieldName();
}
