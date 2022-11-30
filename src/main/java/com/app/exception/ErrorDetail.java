package com.app.exception;

import java.util.Date;
import java.util.Map;

import com.app.response.APIStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@AllArgsConstructor
public class ErrorDetail {
	private Date date;
	private int status;
	private String message;
	private String description;
	private Map<String, String> error;
	
	public ErrorDetail(Date date, APIStatus apiStatus, String description) {
		if (apiStatus == null) {
			throw new IllegalArgumentException("APIStatus must not be null");
		}
		this.status = apiStatus.getStatus();
		this.message = apiStatus.getMessage();
	}

	public ErrorDetail(Date date, int code, String message, String description) {
		this.date = date;
		this.status = code;
		this.message = message;
		this.description = description;
	}

}
