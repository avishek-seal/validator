package io.github.validator.constant;

public enum PANPattern {

	INDIAN("[A-Za-z]{5}\\d{4}[A-Za-z]{1}");
	
	private String value;
	
	private PANPattern(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
