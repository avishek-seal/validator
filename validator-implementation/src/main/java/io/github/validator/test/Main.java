package io.github.validator.test;

import java.util.ArrayList;

import io.github.validator.impl.ValidatorProvider;

public class Main {

	public static void main(String[] args) {
		Employee employee = new Employee();
		
		employee.setDateOfBirth("1990-08-29");
		employee.setEmailId("avishek.seal.90@gmail.com");
		employee.setId(785651);
		employee.setName("Avishek");
		employee.setPanNumber("DJAPS5337G");
		employee.setPhoneNumber("8981497646");
		employee.setChoices(new int[]{1, 2, 3});
		employee.setRoles(new ArrayList<Role>(){

			private static final long serialVersionUID = -3476041215461987786L;

			{
				add(new Role(1, "a"));
				add(new Role(1, null));
			}
		});
		
		try {
			ValidatorProvider.getInstance().validate(employee);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
