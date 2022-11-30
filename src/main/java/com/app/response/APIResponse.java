package com.app.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T extends Object> implements Serializable {

	private static final long serialVersionUID = 7763512169612692145L;
	
	private int status;
	private String message;
	private T data;
 
	public APIResponse(APIStatus apiStatus, T data) {
		if(apiStatus == null) {
			throw new IllegalArgumentException("APIStatus must not be null");
		}
		this.status = apiStatus.getStatus();
		this.message = apiStatus.getMessage();
		this.data = data;
	}
	 
}
