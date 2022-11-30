package com.app.request.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleOrderDetailModel {
	private Long saleOrderDetailId;
	private Long saleOrderId;
	
	private Long productId;
	private BigDecimal price;
	private Integer quantity;
	private BigDecimal amout;
	private BigDecimal discount;
	private BigDecimal totalAmount;
	private Long stockId;

}
