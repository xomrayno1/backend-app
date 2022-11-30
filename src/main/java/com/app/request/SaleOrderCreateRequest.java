package com.app.request;

import java.math.BigDecimal;
import java.util.List;

import com.app.request.model.SaleOrderDetailModel;

import lombok.Data;

/**
 * @author tamnc
 *
 */
@Data
public class SaleOrderCreateRequest {
	private Long customerId;
	private Long staffId;
	private BigDecimal amount;
	private BigDecimal discount;
	private BigDecimal totalAmount;
	private List<SaleOrderDetailModel> saleOrderDetails;
}
