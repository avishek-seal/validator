package com.validator.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.validator.constant.PANPattern;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PANValidator {
	PANPattern pattern() default PANPattern.INDIAN;
	String fieldName();
}
