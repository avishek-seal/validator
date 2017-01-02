package io.github.validator.spec;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.github.validator.constant.ContentType;
import io.github.validator.constant.DatePattern;
import io.github.validator.constant.EmailPattern;
import io.github.validator.exception.DuplicateValueException;
import io.github.validator.exception.InvalidArraySize;
import io.github.validator.exception.InvalidDateException;
import io.github.validator.exception.InvalidEmailException;
import io.github.validator.exception.InvalidTextFormatException;
import io.github.validator.exception.InvalidTextLengthException;
import io.github.validator.exception.NotAnArrayException;
import io.github.validator.exception.NullReferenceFoundException;
import io.github.validator.type.Collection;
import io.github.validator.type.Date;
import io.github.validator.type.Email;
import io.github.validator.type.Model;
import io.github.validator.type.NotNull;
import io.github.validator.type.OptionalText;
import io.github.validator.type.PAN;
import io.github.validator.type.Passport;
import io.github.validator.type.PhoneNumber;
import io.github.validator.type.Pincode;
import io.github.validator.type.TextData;

/**
 * this abstract class holds all the functionality need to implement the specification of
 * the validation
 * 
 * @author Avishek Seal
 * @since Dec 16, 2016
 */
public abstract class AbstractRegisterValidator {
	
	@SuppressWarnings("rawtypes")
	private static final Map<Class, ValidationLogic> VALIDATORS = new HashMap<>();
	
	/**
	 * this method is used to register the validator
	 * with its functionality
	 * 
	 * @param clazz
	 * @param validationLogic
	 */
	protected static <T> void register(Class<T> clazz, ValidationLogic validationLogic) {
		VALIDATORS.put(clazz, validationLogic);
	}
	
	/**
	 * this method is used to get the functionality for a given
	 * validator
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> ValidationLogic getValidators(Class<T> clazz) {
		return VALIDATORS.get(clazz);
	}


	//Register any new validator that you declare under {com.validator.type} package
	static{
		//Registering Date validation logic
		register(Date.class, (annotation, object) -> {
			final String data = object.toString();
			
			final Date dateValidator = Date.class.cast(annotation);
			
			nullCheck(data, dateValidator.fieldName());
			
			dateCheck(data, dateValidator.pattern(), dateValidator.fieldName());
		});
		
		
		//Registering Email validation logic
		register(Email.class, (annotation, object) -> {
			final String data = object.toString();
			
			final Email emailValidator = Email.class.cast(annotation);
			
			nullCheck(data, emailValidator.fieldName());
			
			emailCheck(data, emailValidator.pattern(), emailValidator.fieldName());
		});
		
		
		//Registering Model validation logic
		register(Model.class, (annotation, object) -> {
			final Model modelAnnotation = Model.class.cast(annotation);
			
			if(modelAnnotation.many()) {
				@SuppressWarnings("unchecked")
				java.util.Collection<Object> collection = java.util.Collection.class.cast(object);
				
				for(Object o : collection) {
					modelValidate(o);
				}
				
			} else {
				modelValidate(object);
			}
		});
		
		
		//Registering Not Null validation logic
		register(NotNull.class, (annotation, object) -> {
			nullCheck(object, NotNull.class.cast(annotation).fieldName());
		});
		
		
		//Registering Optional Text validation logic
		register(OptionalText.class, (annotation, object) -> {
			final String data = object.toString();
			
			final OptionalText optionalTextValidator = OptionalText.class.cast(annotation);
			
			lengthCheck(data, optionalTextValidator.minLength(), optionalTextValidator.maxLength(), optionalTextValidator.fieldName());
			
			contentTypeCheck(data, optionalTextValidator.contentType(), optionalTextValidator.fieldName());
		});
		
		
		//Registering PAN Number validation logic
		register(PAN.class, (annotation, object) -> {
			final String data = object.toString();
			
			final PAN panValidator = PAN.class.cast(annotation);
			
			contentTypeCheck(data, panValidator.pattern().getValue(), panValidator.fieldName());
		});
		
		
		//Registering Passport validation logic
		register(Passport.class, (annotation, object) -> {
			
		});
		
		
		//Registering Phone number validation logic
		register(PhoneNumber.class, (annotation, object) -> {
			final String data = object.toString();
			
			final PhoneNumber numberValidator = PhoneNumber.class.cast(annotation);
			
			nullCheck(data, numberValidator.fieldName());
			
			lengthCheck(data, numberValidator.length(), numberValidator.length(),numberValidator.fieldName());
		});
		
		
		//Registering Pincode validation logic
		register(Pincode.class, (annotation, object) -> {
			
		});
		
		
		//Registering TEXT data validation logic
		register(TextData.class, (annotation, object) -> {
			final String data = object.toString();
			
			final TextData dataValidator = TextData.class.cast(annotation);
			
			nullCheck(data, dataValidator.fieldName());
			
			if(dataValidator.lengthCheck()) {
				lengthCheck(data, dataValidator.minLength(), dataValidator.maxLength(), dataValidator.fieldName());	
			}
			
			contentTypeCheck(data, dataValidator.contentType(), dataValidator.fieldName());
		});
		
		//Registering Collection data validation logic
		register(Collection.class, (annotation, object) -> {
			final Collection collection = Collection.class.cast(annotation);
			
			nullCheck(object, collection.fieldName());
			
			if(object.getClass().isArray()) {
				final int length = Array.getLength(object);
				
				if(!(length <= collection.maxElements() && length >= collection.minElements())) {
					throw new InvalidArraySize(length, collection.maxElements(), collection.minElements(), collection.fieldName());
				}
				
				if(collection.uniqueValue()) {
					for(int index = 0; index < length; index++) {
						 for(int nextIndex = index + 1; nextIndex < length; nextIndex++) {
							 if(Array.get(object, index).equals(Array.get(object, nextIndex))) {
								 throw new DuplicateValueException(collection.fieldName(), Array.get(object, index).toString());
							 }
						 }
					 }
				}
			} else {
				throw new NotAnArrayException(collection.fieldName());
			}
		});
	}
	
	//this method is used to validate null
	private static final void nullCheck(Object data, String field) throws NullReferenceFoundException {
		if(Objects.isNull(data)){
			throw new NullReferenceFoundException(field);
		}
	}
	
	//this method is used to validate length
	private static final void lengthCheck(String data, int min, int max, String field) throws InvalidTextLengthException {
		if(max > 0 && min >= 0 && max >= min){
			if(!(max >= data.length() && data.length() >= min)){
				throw new InvalidTextLengthException(max, min, data.length(), field);
			}
		}
	}
	
	//this method is used to validate the content type of a string
	private static void contentTypeCheck(String data, ContentType contentType, String field) throws InvalidTextFormatException {
		if(!data.matches(contentType.getValue())) {
			throw new InvalidTextFormatException(field);
		}
	}
	
	//this method is used to validate the content type of a string
	private static void contentTypeCheck(String data, String pattern, String field) throws InvalidTextFormatException {
		if(!data.matches(pattern)) {
			throw new InvalidTextFormatException(field);
		}
	}
	
	//this method is used to validate the email id
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
	
	//this method is used to validate the object
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
