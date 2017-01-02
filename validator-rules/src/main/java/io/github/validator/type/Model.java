package io.github.validator.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * this validator is used to validate associated object within an object
 * 
 * @author Avishek Seal
 * @since Dec 16, 2016
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Model {
	/**
	 * this option is used to set whether the association is collection or not
	 * @return
	 */
	boolean many() default false;
}
