package application.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import application.business.exceptions.ConfigurationException;
import application.business.exceptions.RecordException;
import application.business.exceptions.WrongDateTimePatternException;
import application.business.exceptions.WrongStringDateFormatException;
import application.common.exceptions.DuplicatedIdException;
import application.common.exceptions.IdNotFoundException;
import application.security.exceptions.AccountManagementException;

@ControllerAdvice
public class CommonControllerAdvice extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = {IdNotFoundException.class, 
			                   DuplicatedIdException.class,
			                   ConfigurationException.class,
			                   RecordException.class,
			                   WrongDateTimePatternException.class,
			                   WrongStringDateFormatException.class,
			                   AccountManagementException.class}) 
	public ResponseEntity<?> handleIdNotFound(RuntimeException runTimeException) {
		 System.out.println(runTimeException.getClass()+" "+runTimeException.getMessage());		
		 return new ResponseEntity<>(runTimeException.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {Exception.class}) 
	public ResponseEntity<?> handleIdNotFound(Exception exception) {
		System.out.println(exception.getClass()+" "+exception.getMessage());		
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
}
}
