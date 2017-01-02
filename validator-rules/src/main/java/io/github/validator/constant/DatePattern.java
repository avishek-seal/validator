package io.github.validator.constant;

public enum DatePattern {

	DATE_HH_MM_SS("yyyy-MM-dd HH:mm:ss")
	, DATE_HH_MM("yyyy-MM-dd HH:mm")
	, DATE("yyyy-MM-dd");
	
	private String value;
	
	private DatePattern(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
