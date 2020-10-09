package org.banxico.dgti.comparadorapp.service.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorUtil {
	private ValidatorUtil() {
	}

	static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	static Validator validator = factory.getValidator();

	@SafeVarargs
	public static <T> Set<ConstraintViolation<T>> validation(T entity, Class<?>... clazz) {
		return validator.validate(entity, clazz);

	}
}
