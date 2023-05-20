package com.cheong.clinic.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class ContactValidator implements ConstraintValidator<Contact, String> {

	@Override
	public boolean isValid(String contact, ConstraintValidatorContext context) {

		char [] contactArr = contact.toCharArray();
		
		if (contactArr[0] == '6' && contactArr[4] == '-') {
			return true;
		}
		return false;
	}

}