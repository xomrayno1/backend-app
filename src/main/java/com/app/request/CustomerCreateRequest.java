package com.app.request;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tamnc
 *
 */
@Getter
@Setter
public class CustomerCreateRequest implements Serializable{
	
	private static final long serialVersionUID = 6818169656940947143L;
	
	@Size(max = 20, message = "")
	@NotBlank(message = "{validation.required}")
	private String customerCode;
	@Size(max = 255, message = "")
	@NotBlank(message = "{validation.required}")
	private String customerName;
	@Size(max = 50, message = "")
	@NotBlank(message = "{validation.required}")
	private String email;
	@Size(max = 12, min = 10, message = "")
	@NotBlank(message = "{validation.required}")
	private String phone;
	@Size(max = 255, message = "")
	@NotBlank(message = "{validation.required}")
	private String address;
	private Date birthDay;
	private int gender;
}
