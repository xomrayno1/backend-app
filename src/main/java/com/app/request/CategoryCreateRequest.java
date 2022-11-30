package com.app.request;

import lombok.Data;

/**
 * @author tamnc
 *
 */
@Data
public class CategoryCreateRequest {
	private String categoryName;
	private String description;
	private String categoryCode;
}	
