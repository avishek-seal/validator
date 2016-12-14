package com.validator.spec;

@FunctionalInterface
public interface ObjectValidator {

	<T> void validate(T t) throws Exception;
}
