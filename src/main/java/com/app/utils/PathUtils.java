package com.app.utils;

public class PathUtils {
	public final static String API_PREFIX_V1 = "/api/v1";

	public final static String PRODUCT_API = API_PREFIX_V1 + "/products";
	public final static String PRODUCT_DELETE = "/product_delete";
	public final static String PRODUCT_UPDATE = "/product_update";
	public final static String PRODUCT_CREATE = "/product_create";
	public final static String PRODUCT_GET_DETAIL = "/product_get_detail/{productId}";
	public final static String PRODUCT_GET_LIST_PAGING_SEARCH_SORT = "/product_get_list_paging_search_sort";
	public final static String PRODUCT_GET_ALL = "/product_get_all";
	
	
	// customer api
	public final static String CUSTOMER_API = API_PREFIX_V1 + "/customers";
	public final static String CUSTOMER_DELETE = "/customer_delete";
	public final static String CUSTOMER_UPDATE = "/customer_update";
	public final static String CUSTOMER_CREATE = "/customer_create";
	public final static String CUSTOMER_GET_DETAIL = "/customer_get_detail/{cusId}";
	public final static String CUSTOMER_GET_LIST_PAGING_SEARCH_SORT = "/customer_get_list_paging_search_sort";

	// stock api
	public final static String SALE_ORDER_API = API_PREFIX_V1 + "/sale_orders";
	public final static String SALE_ORDER_DELETE = "/sale_order_delete";
	public final static String SALE_ORDER_UPDATE = "/sale_order_update";
	public final static String SALE_ORDER_CREATE = "/sale_order_create";
	public final static String SALE_ORDER_GET_DETAIL = "/sale_order_get_detail/{saleOrderId}";
	public final static String SALE_ORDER_GET_LIST_PAGING_SEARCH_SORT = "/sale_order_get_list_paging_search_sort";

	// stock api
	public final static String STOCK_API = API_PREFIX_V1 + "/stocks";
	public final static String STOCK_DELETE = "/stock_delete";
	public final static String STOCK_UPDATE = "/stock_update";
	public final static String STOCK_CREATE = "/stock_create";
	public final static String STOCK_GET_DETAIL = "/stock_get_detail/{stockId}";
	public final static String STOCK_GET_LIST_PAGING_SEARCH_SORT = "/stock_get_list_paging_search_sort";
	
	//staff api
	public final static String STAFF_API = API_PREFIX_V1 + "/staffs";
	public final static String STAFF_DELETE = "/staff_delete";
	public final static String STAFF_UPDATE = "/staff_update";
	public final static String STAFF_CREATE = "/staff_create";
	public final static String STAFF_GET_DETAIL = "/staff_get_detail/{staffId}";
	public final static String STAFF_GET_LIST_PAGING_SEARCH_SORT = "/staff_get_list_paging_search_sort";
	
	//suppliers
	public final static String SUPPLIER_API = API_PREFIX_V1 + "/suppliers";
	public final static String SUPPLIER_DELETE = "/supplier_delete";
	public final static String SUPPLIER_UPDATE = "/supplier_update";
	public final static String SUPPLIER_CREATE = "/supplier_create";
	public final static String SUPPLIER_GET_DETAIL = "/supplier_get_detail/{supplierId}";
	public final static String SUPPLIER_GET_LIST_PAGING_SEARCH_SORT = "/supplier_get_list_paging_search_sort";
	
	//category
	public final static String CATEGORY_API = API_PREFIX_V1 + "/categories";
	public final static String CATEGORY_DELETE = "/category_delete";
	public final static String CATEGORY_UPDATE = "/category_update";
	public final static String CATEGORY_CREATE = "/category_create";
	public final static String CATEGORY_GET_DETAIL = "/category_get_detail/{categoryId}";
	public final static String CATEGORY_GET_All = "/category_all";
	public final static String CATEGORY_GET_LIST_PAGING_SEARCH_SORT = "/category_get_list_paging_search_sort";
	
	//designed_by
	public final static String DESIGNED_BY_API = API_PREFIX_V1 + "/designed_by";
	public final static String DESIGNED_BY_DELETE = "/designed_by_delete";
	public final static String DESIGNED_BY_UPDATE = "/designed_by_update";
	public final static String DESIGNED_BY_CREATE = "/designed_by_create";
	public final static String DESIGNED_BY_GET_DETAIL = "/designed_by_get_detail/{designedById}";
	public final static String DESIGNED_BY_GET_LIST_PAGING_SEARCH_SORT = "/designed_by_get_list_paging_search_sort";
	public final static String DESIGNED_BY_GET_ALL = "/designed_by_get_all";
		
}
