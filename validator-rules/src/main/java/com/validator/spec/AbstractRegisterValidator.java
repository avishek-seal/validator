package com.validator.spec;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.validator.constant.ContentType;
import com.validator.constant.DatePattern;
import com.validator.constant.EmailPattern;
import com.validator.exception.InvalidEmailException;
import com.validator.exception.InvalidTextFormatException;
import com.validator.exception.InvalidTextLengthException;
import com.validator.exception.NullReferenceFoundException;
import com.validator.type.DateValidator;
import com.validator.type.EmailValidator;
import com.validator.type.ModelValidator;
import com.validator.type.NotNullValidator;
import com.validator.type.OptionalTextValidator;
import com.validator.type.PANValidator;
import com.validator.type.PassportValidator;
import com.validator.type.PhoneNumberValidator;
import com.validator.type.PincodeValidator;
import com.validator.type.TextDataValidator;

public abstract class AbstractRegisterValidator {
	
	@SuppressWarnings("rawtypes")
	private static final Map<Class, ValidationLogic> VALIDATORS = new HashMap<>();
	
	
	protected static <T> void register(Class<T> clazz, ValidationLogic validationLogic) {
		VALIDATORS.put(clazz, validationLogic);
	}
	
	public static <T> ValidationLogic getValidators(Class<T> clazz) {
		return VALIDATORS.get(clazz);
	}



	static{
		//Registering Date validation logic
		register(DateValidator.class, (annotation, object) -> {
			final String data = object.toString();
			
			nullCheck(data);
			
			final DateValidator dateValidator = DateValidator.class.cast(annotation);
			
			dateCheck(data, dateValidator.pattern());
		});
		
		
		//Registering Email validation logic
		register(EmailValidator.class, (annotation, object) -> {
			final String data = object.toString();
			
			nullCheck(data);
			
			final EmailValidator emailValidator = EmailValidator.class.cast(annotation);
			
			emailCheck(data, emailValidator.pattern());
		});
		
		
		//Registering Model validation logic
		register(ModelValidator.class, (annotation, object) -> {
			modelValidate(object);
		});
		
		
		//Registering Not Null validation logic
		register(NotNullValidator.class, (annotation, object) -> {
			nullCheck(object.toString());
		});
		
		
		//Registering Optional Text validation logic
		register(OptionalTextValidator.class, (annotation, object) -> {
			final String data = object.toString();
			
			final OptionalTextValidator optionalTextValidator = OptionalTextValidator.class.cast(annotation);
			
			lengthCheck(data, optionalTextValidator.length());
			
			contentTypeCheck(data, optionalTextValidator.contentType());
		});
		
		
		//Registering PAN Number validation logic
		register(PANValidator.class, (annotation, object) -> {
			
		});
		
		
		//Registering Passport validation logic
		register(PassportValidator.class, (annotation, object) -> {
			
		});
		
		
		//Registering Phone number validation logic
		register(PhoneNumberValidator.class, (annotation, object) -> {
			final String data = object.toString();
			
			nullCheck(data);
			
			final PhoneNumberValidator numberValidator = PhoneNumberValidator.class.cast(annotation);
			
			lengthCheck(data, numberValidator.length());
		});
		
		
		//Registering Pincode validation logic
		register(PincodeValidator.class, (annotation, object) -> {
			
		});
		
		
		//Registering TEXT data validation logic
		register(TextDataValidator.class, (annotation, object) -> {
			final String data = object.toString();
			
			nullCheck(data);
			
			final TextDataValidator dataValidator = TextDataValidator.class.cast(annotation);
			
			lengthCheck(data, dataValidator.length());
			
			contentTypeCheck(data, dataValidator.contentType());
		});
	}
	
	private static final void nullCheck(String data) throws NullReferenceFoundException {
		if(Objects.isNull(data)){
			throw new NullReferenceFoundException();
		}
	}
	
	private static final void lengthCheck(String data, int length) throws InvalidTextLengthException {
		if(length > 0){
			if(length < data.length()){
				throw new InvalidTextLengthException(length, data.length());
			}
		}
	}
	
	private static final void contentTypeCheck(String data, ContentType contentType) throws InvalidTextFormatException {
		if(!data.matches(contentType.getValue())) {
			throw new InvalidTextFormatException();
		}
	}
	
	private static final void emailCheck(String data, EmailPattern pattern) throws InvalidEmailException {
		if(!data.matches(pattern.getValue())){
			throw new InvalidEmailException();
		}
	}
	
	//this method is used to check whether date pattern is valid or not
	private static final void dateCheck(String data, DatePattern pattern) throws ParseException {
		final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat();
		DATE_FORMAT.applyPattern(pattern.getValue());
		DATE_FORMAT.parse(data);
	}
	
	protected static final <T> void modelValidate(T t) throws Exception{
		@SuppressWarnings("unchecked")
		final Class<T> modelClass = (Class<T>) t.getClass();
		
		final Field[] fields = modelClass.getDeclaredFields();
		
		if(fields != null && fields.length > 0) {
			for (Field field : fields) {
				final Annotation[] annotations = field.getAnnotations();
				
				if(annotations != null && annotations.length > 0) {
					for(Annotation annotation : annotations) {
						getValidators(annotation.getClass()).logic(annotation, field.get(t));
					}
				}
			}
		}
	}
}
