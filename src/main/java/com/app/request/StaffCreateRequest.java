package com.app.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author tamnc
 *
 */
@Data
public class StaffCreateRequest {
	@NotBlank 
	private String staffCode;
	@NotBlank 
	private String staffName;
	private String email;
	private String phone;
	private String address;
	private Date birthDay;
	@NotBlank 
	private int gender;
	
}
