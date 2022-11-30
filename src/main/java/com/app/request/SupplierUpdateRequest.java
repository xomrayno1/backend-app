package com.app.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author tamnc
 *
 */
@Data
public class SupplierUpdateRequest {
	@NotBlank(message = "{validation.required}")
	private Long id;
	@NotBlank(message = "{validation.required}")
	private String name;
	@NotBlank(message = "{validation.required}")
	private String code;
	@NotBlank(message = "{validation.required}")
	private String phone;
	private String email;
	private String taxCode;
	private String note;
}
