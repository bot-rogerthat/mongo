package com.example.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("emailValidator")
public class EmailValidator {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

	public boolean valid(final String email) {
		if (StringUtils.isNotEmpty(email)) {
			Matcher matcher = PATTERN.matcher(email);
			return matcher.matches();
		}
		return false;
	}
}
