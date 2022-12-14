package com.app.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.response.APIStatus;

@ControllerAdvice
public class HandleException extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors =	result.getFieldErrors();
		
		ErrorDetail errorDetail = new ErrorDetail(new Date(), APIStatus.ERR_BAD_PARAMS,request.getDescription(false));
		
		if(!fieldErrors.isEmpty()) {
			Map<String, String> error = new HashMap<String, String>();
			for (FieldError item : fieldErrors) {
				error.put(item.getField(), item.getDefaultMessage());			
			}
			errorDetail.setError(error);
		}
		
		return new ResponseEntity<Object>(errorDetail, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ApplicationException.class)
	private ResponseEntity<ErrorDetail> handleApplicationException(ApplicationException ae, WebRequest request) {
		ErrorDetail errorDetail = new ErrorDetail(new Date(), ae.getStatus(), ae.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	private ResponseEntity<ErrorDetail> handleException(Exception e) {
		ErrorDetail errorDetail = new ErrorDetail(new Date(), APIStatus.ERR_SYSTEM, null);
		return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.BAD_REQUEST);
	}
}
