package com.app.request.model;

import lombok.Data;

/**
 * @author tamnc
 *
 */
@Data
public class SupplierPagingSearchSortModel {
	private int pageSize;
	private int pageNumber;
	private String searchKey;
}
