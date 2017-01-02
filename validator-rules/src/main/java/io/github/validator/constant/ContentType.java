package io.github.validator.constant;

public enum ContentType {
	ONLY_ALPHABETS("[A-Za-z]+"), ALPHA_NUMERICS("^[A-Z0-9]+$"), NUMERIC("[0-9]+"), DECIMAL("[0-9]*\\.?[0-9]"), ALL("");
	
	private String value;
	
	private ContentType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
