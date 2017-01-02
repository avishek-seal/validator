package io.github.validator.constant;

public enum EmailPattern {
	COMMON_PATTERN("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	
	private String value;
	
	private EmailPattern(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
