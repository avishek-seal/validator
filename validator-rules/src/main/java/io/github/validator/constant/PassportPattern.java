package io.github.validator.constant;

public enum PassportPattern {
	INDIAN("^[A-Z]{1}-[0-9]{7}$");
	
	private String value;
	
	private PassportPattern(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
