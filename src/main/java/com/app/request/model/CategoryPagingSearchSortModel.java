package com.app.request.model;

import lombok.Data;

/**
 * @author tamnc
 *
 */
@Data
public class CategoryPagingSearchSortModel {
	private int pageSize;
	private int pageNumber;
	private String searchKey;
}
