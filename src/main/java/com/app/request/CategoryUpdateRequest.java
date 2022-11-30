package com.app.request;

import lombok.Data;

/**
 * @author tamnc
 *
 */
@Data
public class CategoryUpdateRequest {
	private Long id;
	private String categoryName;
	private String categoryCode;
	private String description;
}
