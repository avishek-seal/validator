package com.validator.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.validator.constant.ContentType;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TextDataValidator {
	int length() default 256;
	ContentType contentType() default ContentType.ALL;
}
