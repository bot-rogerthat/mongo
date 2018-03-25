package com.example.validator;

import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class UserFormValidator implements Validator {

	@Autowired
	@Qualifier("emailValidator")
	private EmailValidator emailValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "userForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dayOfBirth", "userForm.dayOfBirth");
		if (!emailValidator.valid(user.getEmail())) {
			errors.rejectValue("email", "userForm.email");
		}
	}
}