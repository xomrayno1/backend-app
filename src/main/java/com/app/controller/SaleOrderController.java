package com.app.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.SaleOrder;
import com.app.exception.ApplicationException;
import com.app.request.DeleteRequest;
import com.app.request.SaleOrderCreateRequest;
import com.app.request.SaleOrderUpdateRequest;
import com.app.request.model.SaleOrderPagingSearchSortModel;
import com.app.response.APIResponse;
import com.app.response.APIStatus;
import com.app.service.CustomerService;
import com.app.service.SaleOrderService;
import com.app.service.StaffService;
import com.app.utils.PathUtils;
import com.app.utils.ResponseUtil;

@RestController
@RequestMapping(PathUtils.SALE_ORDER_API)
public class SaleOrderController {
	
	private final SaleOrderService saleOrderService;

	private final CustomerService customerService;

	private final StaffService staffService;

	private final ModelMapper modelMapper;
 
	public SaleOrderController(SaleOrderService saleOrderService, CustomerService customerService,
			StaffService staffService, ModelMapper modelMapper) {
		this.saleOrderService = saleOrderService;
		this.modelMapper = modelMapper;
		this.customerService = customerService;
		this.staffService = staffService;
	}

	/**
	 * paging , search saleOrder by name ...
	 * 
	 * @author tamnc
	 */
	@PostMapping(PathUtils.SALE_ORDER_GET_LIST_PAGING_SEARCH_SORT)
	public ResponseEntity<APIResponse> getListPagingSearchSort(@RequestBody SaleOrderPagingSearchSortModel ppss) {
		Page<SaleOrder> saleOrders = saleOrderService.findAll(ppss);
		return ResponseUtil.responseSuccess(saleOrders);
	}

	/**
	 * get detail saleOrder
	 * 
	 * @author tamnc
	 */
	@GetMapping(PathUtils.SALE_ORDER_GET_DETAIL)
	public ResponseEntity<APIResponse> getDetail(@PathVariable("saleOrderId") Long saleOrderId) {
		SaleOrder saleOrder = saleOrderService.findBySaleOrderId(saleOrderId)
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_SALE_ORDER_NOT_EXIST));
		return ResponseUtil.responseSuccess(saleOrder);
	}

	/**
	 * update saleOrder
	 * 
	 * @author tamnc
	 */
	@PutMapping(PathUtils.SALE_ORDER_UPDATE)
	public ResponseEntity<APIResponse> saleOrderUpdate(
			@Validated @RequestBody SaleOrderUpdateRequest saleOrderUpdateRequest) {

		SaleOrder saleOrder = saleOrderService.findBySaleOrderId(saleOrderUpdateRequest.getSaleOrderId())
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_SALE_ORDER_NOT_EXIST));

		customerService.findById(saleOrderUpdateRequest.getCustomerId())
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_CUSTOMER_NOT_EXIST));

		staffService.findById(saleOrderUpdateRequest.getStaffId())
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_STAFF_NOT_EXIST));

		// update saleOrder
		saleOrderService.update(saleOrder, saleOrderUpdateRequest);

		return ResponseUtil.responseSuccess(APIStatus.SUCCESS_UPDATE);
	}

	/**
	 * create saleOrder
	 * 
	 * @author tamnc
	 */
	@PostMapping(PathUtils.SALE_ORDER_CREATE)
	public ResponseEntity<APIResponse> saleOrderCreate(
			@Validated @RequestBody SaleOrderCreateRequest saleOrderCreateRequest) {

		customerService.findById(saleOrderCreateRequest.getCustomerId())
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_CUSTOMER_NOT_EXIST));

		staffService.findById(saleOrderCreateRequest.getStaffId())
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_STAFF_NOT_EXIST));

		saleOrderService.create(saleOrderCreateRequest);
		return ResponseUtil.responseSuccess(APIStatus.SUCCESS_CREATE);
	}

	/**
	 * delete saleOrder
	 * 
	 * @author tamnc
	 */
	@DeleteMapping(PathUtils.SALE_ORDER_DELETE)
	public ResponseEntity<APIResponse> saleOrderDelete(@RequestBody DeleteRequest deleteRequest) {

		List<SaleOrder> saleOrders = saleOrderService.findAllBySaleOrderId(deleteRequest.getIds());

		if (!saleOrders.isEmpty()) {
			saleOrderService.deleteAll(saleOrders);
			return ResponseUtil.responseSuccess(APIStatus.SUCCESS_DELETE);
		} else {
			throw new ApplicationException(APIStatus.ERR_ID_SALE_ORDER_NOT_EXIST);
		}
	}
}
