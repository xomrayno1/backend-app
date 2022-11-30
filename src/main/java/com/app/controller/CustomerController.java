package com.app.controller;

import java.util.List;
import java.util.Optional;

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

import com.app.entity.Customer;
import com.app.exception.ApplicationException;
import com.app.request.CustomerCreateRequest;
import com.app.request.CustomerUpdateRequest;
import com.app.request.DeleteRequest;
import com.app.request.model.CustomerPagingSearchSortModel;
import com.app.response.APIResponse;
import com.app.response.APIStatus;
import com.app.service.CustomerService;
import com.app.utils.PathUtils;
import com.app.utils.ResponseUtil;

/**
 * define customer api
 * @author tamnc
 */
@RestController
@RequestMapping(PathUtils.CUSTOMER_API)
public class CustomerController{
	
	private final CustomerService customerService;
	
	private final ModelMapper modelMapper;
 
	public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
		this.customerService = customerService;
		this.modelMapper = modelMapper;
	}

	/**
	 * paging , search customer by name ...
	 * 
	 * @author tamnc
	 */
	@PostMapping(PathUtils.CUSTOMER_GET_LIST_PAGING_SEARCH_SORT)
	public ResponseEntity<APIResponse> getListPagingSearchSort(@RequestBody CustomerPagingSearchSortModel ppss) {
		Page<Customer> customers = customerService.findAll(ppss);
		return ResponseUtil.responseSuccess(customers);
	}
	
	/**
	 * get detail customer
	 * 
	 * @author tamnc
	 */
	@GetMapping(PathUtils.CUSTOMER_GET_DETAIL)
	public ResponseEntity<APIResponse> getDetail(@PathVariable("cusId") Long cusId) {
		Customer customer = customerService.findById(cusId)
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_CUSTOMER_NOT_EXIST));
		return ResponseUtil.responseSuccess(customer);
	}

	/**
	 * update customer
	 * 
	 * @author tamnc
	 */
	@PutMapping(PathUtils.CUSTOMER_UPDATE)
	public ResponseEntity<APIResponse> cusUpdate(@Validated @RequestBody CustomerUpdateRequest cusUpdateRequest) {

		Customer customer = customerService.findById(cusUpdateRequest.getId())
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_CUSTOMER_NOT_EXIST));

		// check customer code exist
		if (!cusUpdateRequest.getCustomerCode().equals(customer.getCustomerCode())) {
			Optional<Customer> cusOptional = customerService.findByCustomerCode(cusUpdateRequest.getCustomerCode());
			if (cusOptional.isPresent()) {
				throw new ApplicationException(APIStatus.ERR_CODE_CUSTOMER_ALREADY_EXISTS);
			}
		}
		// trans request to entity
		customer = modelMapper.map(cusUpdateRequest, Customer.class);
		
		// update customer
		customerService.update(customer);
		return ResponseUtil.responseSuccess(APIStatus.SUCCESS_UPDATE);
	}

	/**
	 * create customer
	 * 
	 * @author tamnc
	 */
	@PostMapping(PathUtils.CUSTOMER_CREATE)
	public ResponseEntity<APIResponse> cusCreate(@Validated @RequestBody CustomerCreateRequest cusCreateRequest) {

		Optional<Customer> cusOptional = customerService.findByCustomerCode(cusCreateRequest.getCustomerCode());
		if (cusOptional.isPresent()) {
			throw new ApplicationException(APIStatus.ERR_CODE_CUSTOMER_ALREADY_EXISTS);
		} else {
			Customer customer = modelMapper.map(cusCreateRequest, Customer.class);
			customerService.create(customer);
			return ResponseUtil.responseSuccess(APIStatus.SUCCESS_CREATE);
		}
	}

	/**
	 * delete customer
	 * 
	 * @author tamnc
	 */
	@DeleteMapping(PathUtils.CUSTOMER_DELETE)
	public ResponseEntity<APIResponse> cusDelete(@RequestBody DeleteRequest deleteRequest) {
		List<Customer> customers = customerService.findAllByIds(deleteRequest.getIds());
		
		if (!customers.isEmpty()) {
			customerService.deleteAll(Customer.class, customers);
			return ResponseUtil.responseSuccess(APIStatus.SUCCESS_DELETE);
		} else {
			throw new ApplicationException(APIStatus.ERR_ID_CUSTOMER_NOT_EXIST);
		}
	}
 
}
