package com.example.util;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class TimestampPropertyEditor extends PropertyEditorSupport {
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat(TimestampPropertyEditor.DEFAULT_BATCH_PATTERN);
	private static final String DEFAULT_BATCH_PATTERN = "yyyy-MM-dd";

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			setValue(new Timestamp(FORMAT.parse(text).getTime()));
		} catch (ParseException ex) {
			setValue(null);
		}
	}

	@Override
	public String getAsText() {
		Timestamp value = (Timestamp) getValue();
		return (value != null ? FORMAT.format(value) : "");
	}
}
