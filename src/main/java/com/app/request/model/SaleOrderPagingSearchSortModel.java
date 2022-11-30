package com.app.request.model;

import java.util.Date;

import lombok.Data;

/**
 * @author tamnc
 *
 */
@Data
public class SaleOrderPagingSearchSortModel {
	private int pageSize;
	private int pageNumber;
	private Date dateFrom;
	private Date dateTo;
}
