package com.validator.spec;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.validator.constant.ContentType;
import com.validator.constant.DatePattern;
import com.validator.constant.EmailPattern;
import com.validator.exception.InvalidDateException;
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
			
			final DateValidator dateValidator = DateValidator.class.cast(annotation);
			
			nullCheck(data, dateValidator.fieldName());
			
			dateCheck(data, dateValidator.pattern(), dateValidator.fieldName());
		});
		
		
		//Registering Email validation logic
		register(EmailValidator.class, (annotation, object) -> {
			final String data = object.toString();
			
			final EmailValidator emailValidator = EmailValidator.class.cast(annotation);
			
			nullCheck(data, emailValidator.fieldName());
			
			emailCheck(data, emailValidator.pattern(), emailValidator.fieldName());
		});
		
		
		//Registering Model validation logic
		register(ModelValidator.class, (annotation, object) -> {
			modelValidate(object);
		});
		
		
		//Registering Not Null validation logic
		register(NotNullValidator.class, (annotation, object) -> {
			nullCheck(object.toString(), NotNullValidator.class.cast(annotation).fieldName());
		});
		
		
		//Registering Optional Text validation logic
		register(OptionalTextValidator.class, (annotation, object) -> {
			final String data = object.toString();
			
			final OptionalTextValidator optionalTextValidator = OptionalTextValidator.class.cast(annotation);
			
			lengthCheck(data, optionalTextValidator.length(), optionalTextValidator.fieldName());
			
			contentTypeCheck(data, optionalTextValidator.contentType(), optionalTextValidator.fieldName());
		});
		
		
		//Registering PAN Number validation logic
		register(PANValidator.class, (annotation, object) -> {
			final String data = object.toString();
			
			final PANValidator panValidator = PANValidator.class.cast(annotation);
			
			contentTypeCheck(data, panValidator.pattern().getValue(), panValidator.fieldName());
		});
		
		
		//Registering Passport validation logic
		register(PassportValidator.class, (annotation, object) -> {
			
		});
		
		
		//Registering Phone number validation logic
		register(PhoneNumberValidator.class, (annotation, object) -> {
			final String data = object.toString();
			
			final PhoneNumberValidator numberValidator = PhoneNumberValidator.class.cast(annotation);
			
			nullCheck(data, numberValidator.fieldName());
			
			lengthCheck(data, numberValidator.length(), numberValidator.fieldName());
		});
		
		
		//Registering Pincode validation logic
		register(PincodeValidator.class, (annotation, object) -> {
			
		});
		
		
		//Registering TEXT data validation logic
		register(TextDataValidator.class, (annotation, object) -> {
			final String data = object.toString();
			
			final TextDataValidator dataValidator = TextDataValidator.class.cast(annotation);
			
			nullCheck(data, dataValidator.fieldName());
			
			if(dataValidator.lengthCheck()) {
				lengthCheck(data, dataValidator.length(), dataValidator.fieldName());	
			}
			
			contentTypeCheck(data, dataValidator.contentType(), dataValidator.fieldName());
		});
	}
	
	private static final void nullCheck(String data, String field) throws NullReferenceFoundException {
		if(Objects.isNull(data)){
			throw new NullReferenceFoundException(field);
		}
	}
	
	private static final void lengthCheck(String data, int length, String field) throws InvalidTextLengthException {
		if(length > 0){
			if(length > data.length()){
				throw new InvalidTextLengthException(length, data.length(), field);
			}
		}
	}
	
	private static void contentTypeCheck(String data, ContentType contentType, String field) throws InvalidTextFormatException {
		if(!data.matches(contentType.getValue())) {
			throw new InvalidTextFormatException(field);
		}
	}
	
	private static void contentTypeCheck(String data, String pattern, String field) throws InvalidTextFormatException {
		if(!data.matches(pattern)) {
			throw new InvalidTextFormatException(field);
		}
	}
	
	private static final void emailCheck(String data, EmailPattern pattern, String field) throws InvalidEmailException {
		if(!data.matches(pattern.getValue())){
			throw new InvalidEmailException(field);
		}
	}
	
	//this method is used to check whether date pattern is valid or not
	private static final void dateCheck(String data, DatePattern pattern, String field) throws Exception {
		final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat();
		DATE_FORMAT.applyPattern(pattern.getValue());
		
		try{
			DATE_FORMAT.parse(data);
		} catch (Exception e) {
			throw new InvalidDateException(field);
		}
	}
	
	protected static final <T> void modelValidate(T t) throws Exception{
		@SuppressWarnings("unchecked")
		final Class<T> modelClass = (Class<T>) t.getClass();
		
		final Field[] fields = modelClass.getDeclaredFields();
		
		if(fields != null && fields.length > 0) {
			for (Field field : fields) {
				final Annotation[] annotations = field.getAnnotations();
				field.setAccessible(true);
				if(annotations != null && annotations.length > 0) {
					for(Annotation annotation : annotations) {
						getValidators(annotation.annotationType()).logic(annotation, field.get(t));
					}
				}
			}
		}
	}
}
