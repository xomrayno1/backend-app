package com.app.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author tamnc
 *
 */
@Data
public class SupplierCreateRequest {
	@NotBlank 
	private String name;
	@NotBlank 
	private String code;
	@NotBlank
	private String phone;
	private String email;
	private String taxCode;
	private String note;
}
