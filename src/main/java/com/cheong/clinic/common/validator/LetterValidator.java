package com.cheong.clinic.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LetterValidator implements ConstraintValidator<Letter, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		boolean isLetter = false;
		
		char [] valueArr = value.toCharArray();
		
		for(int i = 0;i < valueArr.length - 1 ;i++) {
			if(Character.isLetter(valueArr[i]) || Character.isSpace(valueArr[i])) {
				isLetter = true;
			}
			else {
				isLetter = false;
				break;
			}
		}
		return isLetter;
	}

}
