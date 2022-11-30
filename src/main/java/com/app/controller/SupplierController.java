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

import com.app.entity.Supplier;
import com.app.exception.ApplicationException;
import com.app.request.DeleteRequest;
import com.app.request.SupplierCreateRequest;
import com.app.request.SupplierUpdateRequest;
import com.app.request.model.SupplierPagingSearchSortModel;
import com.app.response.APIResponse;
import com.app.response.APIStatus;
import com.app.service.SupplierService;
import com.app.utils.PathUtils;
import com.app.utils.ResponseUtil;

/**
 * @author tamnc
 *
 */
@RestController
@RequestMapping(PathUtils.SUPPLIER_API)
public class SupplierController {
	
	private final SupplierService supplierService;
	
	private final ModelMapper modelMapper;
 
	public SupplierController(SupplierService supplierService, ModelMapper modelMapper) {
		this.supplierService = supplierService;
		this.modelMapper = modelMapper;
	}

	/**
	 * paging , search supplier by name ...
	 * 
	 * @author tamnc
	 */
	@PostMapping(PathUtils.SUPPLIER_GET_LIST_PAGING_SEARCH_SORT)
	public ResponseEntity<APIResponse> getListPagingSearchSort(@RequestBody SupplierPagingSearchSortModel ppss) {
		Page<Supplier> suppliers = supplierService.findAll(ppss);
		return ResponseUtil.responseSuccess(suppliers);
	}

	/**
	 * get detail supplier
	 * 
	 * @author tamnc
	 */
	@GetMapping(PathUtils.SUPPLIER_GET_DETAIL)
	public ResponseEntity<APIResponse> getDetail(@PathVariable("supplierId") Long supplierId) {
		Supplier supplier = supplierService.findById(supplierId)
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_SUPPLIER_NOT_EXIST));
		return ResponseUtil.responseSuccess(supplier);
	}

	/**
	 * update supplier
	 * 
	 * @author tamnc
	 */
	@PutMapping(PathUtils.SUPPLIER_UPDATE)
	public ResponseEntity<APIResponse> supplierUpdate(
			@Validated @RequestBody SupplierUpdateRequest supplierUpdateRequest) {

		Supplier supplier = supplierService.findById(supplierUpdateRequest.getId())
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_SUPPLIER_NOT_EXIST));

		if (!supplierUpdateRequest.getCode().equals(supplier.getCode())) {
			Optional<Supplier> supOptional = supplierService.findByCode(supplierUpdateRequest.getCode());
			if (supOptional.isPresent()) {
				throw new ApplicationException(APIStatus.ERR_CODE_SUPPLIER_ALREADY_EXISTS);
			}
		}
		// trans request to entity
		supplier = modelMapper.map(supplierUpdateRequest, Supplier.class);

		// update supplier
		supplierService.update(supplier);

		return ResponseUtil.responseSuccess(APIStatus.SUCCESS_UPDATE);
	}

	/**
	 * create supplier
	 * 
	 * @author tamnc
	 */
	@PostMapping(PathUtils.SUPPLIER_CREATE)
	public ResponseEntity<APIResponse> cusCreate(@Validated @RequestBody SupplierCreateRequest supplierCreateRequest) {
		
		Optional<Supplier> supOptional = supplierService.findByCode(supplierCreateRequest.getCode());
		if (supOptional.isPresent()) {
			throw new ApplicationException(APIStatus.ERR_CODE_SUPPLIER_ALREADY_EXISTS);
		} else {
			Supplier createSupplier = modelMapper.map(supplierCreateRequest, Supplier.class);
			supplierService.create(createSupplier);
			return ResponseUtil.responseSuccess(APIStatus.SUCCESS_CREATE);
		}
	}

	/**
	 * delete supplier
	 * 
	 * @author tamnc
	 */
	@DeleteMapping(PathUtils.SUPPLIER_DELETE)
	public ResponseEntity<APIResponse> cusDelete(@RequestBody DeleteRequest deleteRequest) {

		List<Supplier> suppliers = supplierService.findAllByIds(deleteRequest.getIds());
		 
		if (suppliers != null && !suppliers.isEmpty()) {
			supplierService.deleteAll(Supplier.class, suppliers);
			return ResponseUtil.responseSuccess(APIStatus.SUCCESS_DELETE);
		} else {
			throw new ApplicationException(APIStatus.ERR_ID_SUPPLIER_NOT_EXIST);
		}
	}
}
