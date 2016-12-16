# validator

- This is an API which helps to validate the Java POJO with providing annotation over the properties and customizing the
required validation criteria (Annotaion's optional properities).

- It is easy to import to your project.

Example:
::::::::::::::::::::::::::::::::::::::::::::::::::::: START OF POJO CLASS :::::::::::::::::::::::::::::::::::::::::::::::::::::
import com.validator.constant.ContentType;
import com.validator.constant.DatePattern;
import com.validator.constant.PANPattern;
import com.validator.type.DateValidator;
import com.validator.type.EmailValidator;
import com.validator.type.PANValidator;
import com.validator.type.PhoneNumberValidator;
import com.validator.type.TextDataValidator;

public class Employee {

	@TextDataValidator(contentType=ContentType.NUMERIC, minLength = 5, maxLength = 8, fieldName = "Employee Id")
	private String id;
	
	@TextDataValidator(contentType = ContentType.ONLY_ALPHABETS, lengthCheck = false, fieldName = "Name")
	private String name;
	
	@DateValidator(pattern = DatePattern.DATE, fieldName = "Date of Birth")
	private String dateOfBirth;
	
	@PANValidator(pattern = PANPattern.INDIAN, fieldName = "PAN")
	private String panNumber;
	
	@EmailValidator(fieldName = "Professional Email Id")
	private String emailId;
	
	@PhoneNumberValidator(countryCode = false, length = 10, fieldName = "Professional Mobile Number")
	private String phoneNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
}

:::::::::::::::::::::::::::::::::::::::::::::::::::::: END OF POJO CLASS :::::::::::::::::::::::::::::::::::::::::::::::

::::::::::::::::::::::::::::::::::::::::::::: START OF HOW TO USE ::::::::::::::::::::::::::::::::::::::::::::::::::::::
package com.validator.test;

import com.validator.impl.ValidatorProvider;

public class Main {

	public static void main(String[] args) {
		Employee employee = new Employee();
		
		employee.setDateOfBirth("1990-7-29");
		employee.setEmailId("avishek.seal.90@gmail.com");
		employee.setId("78155");
		employee.setName("Avishek");
		employee.setPanNumber("DJAPS5337G");
		employee.setPhoneNumber("8981497646");
		
		try {
			ValidatorProvider.getInstance().validate(employee);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
::::::::::::::::::::::::::::::::::::::::::::: END OF HOW TO USE ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
