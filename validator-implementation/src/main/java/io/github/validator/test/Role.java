package io.github.validator.test;

import io.github.validator.type.NotNull;

public class Role {

	@NotNull(fieldName = "Roll Id")
	private int id;
	
	@NotNull(fieldName = "Role Name")
	private String rollName;

	public Role(int id, String rollName) {
		super();
		this.id = id;
		this.rollName = rollName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRollName() {
		return rollName;
	}

	public void setRollName(String rollName) {
		this.rollName = rollName;
	}
}
