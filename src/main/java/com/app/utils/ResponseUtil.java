package com.app.utils;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.response.APIResponse;
import com.app.response.APIStatus;

public class ResponseUtil {
	public static APIResponse createAPIResponse(APIStatus apiStatus, Object data) {
		return new APIResponse(apiStatus, data); 
	}
	
	public static ResponseEntity<APIResponse> buildResponse(APIStatus apiStatus, Object data, HttpStatus httpStatus){
		return new ResponseEntity<APIResponse>(createAPIResponse(apiStatus, data), httpStatus);
	}
	
	public static ResponseEntity<APIResponse> responseSuccess(Object data){
		return buildResponse(APIStatus.OK, data, HttpStatus.OK);
	}

	public static ResponseEntity<APIResponse> responseSuccess(APIStatus apiStatus){
		return buildResponse(apiStatus, Collections.EMPTY_LIST, HttpStatus.OK);
	}
}
