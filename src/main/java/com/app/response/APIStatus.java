package com.app.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum APIStatus {
	OK(200, "OK"),
	
	ERR_BAD_PARAMS(400, "Bad params"),
	
	ERR_UNAUTHORIZED(401, "Username hoặc password không đúng"),
	
	ERR_LOGOUT(111, "You're not logged"),
	
	ERR_SYSTEM(101, "Có lỗi trong quá trình xử lý"),
	 
	// production
	//en: List production is empty
	ERR_LIST_PRODUCT_IS_EMPTY(550, "List production is empty"),
	//en: Id production not exist
	ERR_ID_PRODUCT_NOT_EXIST(551, "Id production not exist"),
	//en: Code production already exists
	ERR_CODE_PRODUCT_ALREADY_EXISTS(552, "Code production already exists"),

	//stock error
	ERR_LIST_SALE_ORDER_IS_EMPTY(601, "List stock is empty"),
	ERR_ID_SALE_ORDER_NOT_EXIST(602, "Id stock not exist"),
	
	//stock error
	ERR_LIST_STOCK_IS_EMPTY(601, "List stock is empty"),
	ERR_ID_STOCK_NOT_EXIST(602, "Id stock not exist"),
	ERR_CODE_STOCK_ALREADY_EXISTS(603, "Code stock already exists"),
	
	//stock error
	ERR_LIST_CUSTOMER_IS_EMPTY(601, "List customer is empty"),
	ERR_ID_CUSTOMER_NOT_EXIST(602, "Id customer not exist"),
	ERR_CODE_CUSTOMER_ALREADY_EXISTS(603, "Code customer already exists"),
	
	// staff error
	ERR_LIST_STAFF_IS_EMPTY(601, "List staff is empty"), 
	ERR_ID_STAFF_NOT_EXIST(602, "Id staff not exist"),
	ERR_CODE_STAFF_ALREADY_EXISTS(603, "Code staff already exists"),
	
	ERR_LIST_SUPPLIER_IS_EMPTY(601, "List supplier is empty"), 
	ERR_ID_SUPPLIER_NOT_EXIST(602, "Id supplier not exist"),
	ERR_CODE_SUPPLIER_ALREADY_EXISTS(603, "Code supplier already exists"),
	
	ERR_ID_STOCK_AND_PRODUCT_NOT_EXIST(602, "Id stock and product not exist"),
	
	//category
	ERR_LIST_CATEGORY_IS_EMPTY(601, "List category is empty"), 
	ERR_ID_CATEGORY_NOT_EXIST(602, "Id category not exist"),
	ERR_CODE_CATEGORY_ALREADY_EXISTS(603, "Code category already exists"),
	//##
	ERR_LIST_DESIGNED_BY_IS_EMPTY(601, "List designed by is empty"), 
	ERR_ID_DESIGNED_BY_NOT_EXIST(602, "Id designed by not exist"),
	ERR_CODE_DESIGNED_BY_ALREADY_EXISTS(603, "Code designed by already exists"),
	
	
	SUCCESS_UPDATE(200, "update success"),
	SUCCESS_CREATE(200, "create success"),
	SUCCESS_DELETE(200, "delete success"),
	;
	
	private final int status;
	private final String message;
  
}
