package com.cheong.clinic.common.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EmailValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(String.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		
		
	}

}
