package com.app.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author tamnc
 *
 */
@Data
public class StockCreateRequest {
	@NotBlank
	private String name;
	@NotBlank
	private String code;
	private String note;
	private String address;
}
