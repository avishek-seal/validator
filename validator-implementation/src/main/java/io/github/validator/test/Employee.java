package io.github.validator.test;

import java.util.List;

import io.github.validator.constant.ContentType;
import io.github.validator.constant.DatePattern;
import io.github.validator.constant.PANPattern;
import io.github.validator.type.Collection;
import io.github.validator.type.Email;
import io.github.validator.type.Model;
import io.github.validator.type.PAN;
import io.github.validator.type.PhoneNumber;
import io.github.validator.type.TextData;

public class Employee {

	@TextData(contentType=ContentType.NUMERIC, minLength = 5, maxLength = 8, fieldName = "Employee Id")
	private int id;
	
	@TextData(contentType = ContentType.ONLY_ALPHABETS, lengthCheck = false, fieldName = "Name")
	private String name;
	
	@io.github.validator.type.Date(pattern = DatePattern.DATE, fieldName = "Date of Birth")
	private String dateOfBirth;
	
	@PAN(pattern = PANPattern.INDIAN, fieldName = "PAN")
	private String panNumber;
	
	@Email(fieldName = "Professional Email Id")
	private String emailId;
	
	@PhoneNumber(countryCode = false, length = 10, fieldName = "Professional Mobile Number")
	private String phoneNumber;
	
	@Collection(fieldName = "Choices", uniqueValue = true, minElements = 3, maxElements = 10)
	private int[] choices;

	@Model(many = true)
	private List<Role> roles;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int[] getChoices() {
		return choices;
	}

	public void setChoices(int[] choices) {
		this.choices = choices;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
