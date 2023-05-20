package cheong.clinic_management_system.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SSNValidator implements ConstraintValidator<SSN, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if(value.length() == 12) {
			return true;
		}
		return false;
	}

	
}
