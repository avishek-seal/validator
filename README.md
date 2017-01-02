# validator

- This is an API which helps to validate the Java POJO with providing annotation over the properties and customizing the
required validation criteria (Annotaion's optional properities).

- It is easy to import to your project.

- Types of validator it supports:
	
		TYPE			OPTIONS  
  ** @Collection	[fieldName - String], [maxElements - int], [minElements - int], [uniqueValue - boolean]
  ** @Date			[fieldName - String], [pattern - io.github.validator.constant.DatePattern]
  ** @Email			[fieldName - String], [pattern - io.github.validator.constant.EmailPattern]
  ** @Model			[many - boolean]
  ** @NotNull		[fieldName - String]
  ** @OptionalText	[fieldName - String], [contentType - io.github.validator.constant.ContentType], [maxLength - int], [minLength - int]
  ** @PAN			[fieldName - String], [pattern - io.github.validator.constant.PANPattern]
  ** @Passport		[fieldName - String], [pattern - io.github.validator.constant.PassportPattern]
  ** @PhoneNumber	[fieldName - String], [countryCode - boolean], [length - int]
  ** @Pincode		[fieldName - String]
  ** @TextData		[fieldName - String], [contentType - io.github.validator.constant.ContentType], [lengthCheck - boolean], [maxLength - int], [minLength - int]
  Eexample:
::::::::::::::::::::::::::::::::::::::::::::::::::::: START OF POJO CLASS :::::::::::::::::::::::::::::::::::::::::::::::::::::

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
	private Date dateOfBirth;
	
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

:::::::::::::::::::::::::::::::::::::::::::::::::::::: END OF POJO CLASS :::::::::::::::::::::::::::::::::::::::::::::::

::::::::::::::::::::::::::::::::::::::::::::: START OF HOW TO USE ::::::::::::::::::::::::::::::::::::::::::::::::::::::
			
			Employee employee = new Employee();
			employee.setDateOfBirth("1980-7-29");
			employee.setEmailId("abc90@xyz.com");
			employee.setId("78155");
			employee.setName("Avishek");
			employee.setPanNumber("EPBMS1234K");
			employee.setPhoneNumber("9999999999");
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
			}
			
::::::::::::::::::::::::::::::::::::::::::::: END OF HOW TO USE ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
