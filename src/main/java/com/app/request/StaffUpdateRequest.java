package com.app.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author tamnc
 *
 */
@Data
public class StaffUpdateRequest {
	@NotBlank(message = "{validation.required}")
	private Long id;
	@NotBlank(message = "{validation.required}")
	private String staffCode;
	@NotBlank(message = "{validation.required}")
	private String staffName;
	private String email;
	private String phone;
	private String address;
	private Date birthDay;
	@NotBlank(message = "{validation.required}")
	private int gender;
 
}
