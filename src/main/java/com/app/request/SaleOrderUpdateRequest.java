package com.app.request;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.app.request.model.SaleOrderDetailModel;

import lombok.Data;

/**
 * @author tamnc
 *
 */
@Data
public class SaleOrderUpdateRequest {
	private Long saleOrderId;
	private Long customerId;
	private Long staffId;
	private BigDecimal amount;
	private BigDecimal discount;
	private BigDecimal totalAmount;
	private Date orderDate;
	private String orderNumber;
	private int status;
	private List<SaleOrderDetailModel> saleOrderDetails;
}
