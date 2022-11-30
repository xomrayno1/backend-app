package com.app.request.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPagingSearchSortModel {
	private Long supplierId;
	private String searchKey;
	private Long categoryId;
	private int pageSize;
	private int pageNumber;
}
