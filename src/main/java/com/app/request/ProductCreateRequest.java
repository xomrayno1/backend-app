package com.app.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateRequest {
	@Size(max = 255)
	@NotBlank
	private String productName;
	@Size(max = 32)
	@NotBlank
	private String productCode;
//	@Size(max = 11)
//	@NotBlank
	private BigDecimal productPrice;
	private Long categoryId;
	private Long designedById;
	private String productNote;
}
