package com.app.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DesignedByUpdateRequest {
	private Long id;
	 
	private String name;
	
	private String code;
 
	private String phone;
 
	private String note;
}
