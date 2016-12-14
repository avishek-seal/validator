package com.validator.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.validator.constant.EmailPattern;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EmailValidator{
	EmailPattern pattern() default EmailPattern.COMMON_PATTERN;
}
