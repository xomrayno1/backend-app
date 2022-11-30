package com.app.request.model;

import lombok.Data;

/**
 * @author tamnc
 *
 */
@Data
public class CustomerPagingSearchSortModel {
	private int pageSize;
	private int pageNumber;
	private String searchKey;
	private String phone;

}
