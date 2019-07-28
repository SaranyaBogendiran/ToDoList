package com.saranya.microservice.todolist.Exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;







@ControllerAdvice
@RestController
public class CustomExpectionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
		ErrorResponse errorResponse = new ErrorResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false)
				);
		
		return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity handleUserNotFoundException(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(true)
				);
			return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND );
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		ErrorResponse errorResponse = new ErrorResponse(
	            new Date(),
				"Validation Failed",
				ex.getBindingResult().toString()
				);
		return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
		
	}
	
}
