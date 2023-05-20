package com.cheong.clinic.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<Gender, Character>{

	@Override
	public boolean isValid(Character value, ConstraintValidatorContext context) {
		
		if(value == 'M' || value == 'F') {
			return true;
		}
		return false;
	}

}
