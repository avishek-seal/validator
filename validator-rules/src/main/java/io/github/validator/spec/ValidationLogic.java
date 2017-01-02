package io.github.validator.spec;

import java.lang.annotation.Annotation;

@FunctionalInterface
public interface ValidationLogic {

	void logic(Annotation annotation, Object object) throws Exception;
}
