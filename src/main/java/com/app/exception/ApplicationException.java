package com.app.exception;

import com.app.response.APIStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApplicationException extends RuntimeException{
	
	private static final long serialVersionUID = 4776525260196050530L;
	
	private final int status;
	private final String message;
	
	public ApplicationException(APIStatus apiStatus) {
		if(apiStatus == null) {
			 throw new IllegalArgumentException("APIStatus must not be null");
		}
		status = apiStatus.getStatus();
		message = apiStatus.getMessage();
	}
	
}
