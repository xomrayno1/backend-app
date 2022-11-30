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

import com.app.entity.Staff;
import com.app.exception.ApplicationException;
import com.app.request.DeleteRequest;
import com.app.request.StaffCreateRequest;
import com.app.request.StaffUpdateRequest;
import com.app.request.model.StaffPagingSearchSortModel;
import com.app.response.APIResponse;
import com.app.response.APIStatus;
import com.app.service.StaffService;
import com.app.utils.PathUtils;
import com.app.utils.ResponseUtil;

/**
 * @author tamnc
 *
 */
@RestController
@RequestMapping(PathUtils.STAFF_API)
public class StaffController {
	
	private final StaffService staffService;

	private final ModelMapper modelMapper;
 
	public StaffController(StaffService staffService, ModelMapper modelMapper) {
		this.staffService = staffService;
		this.modelMapper = modelMapper;
	}

	/**
	 * paging , search staff by name ...
	 * 
	 * @author tamnc
	 */
	@PostMapping(PathUtils.STAFF_GET_LIST_PAGING_SEARCH_SORT)
	public ResponseEntity<APIResponse> getListPagingSearchSort(@RequestBody StaffPagingSearchSortModel ppss) {
		Page<Staff> staffs = staffService.findAll(ppss);
		return ResponseUtil.responseSuccess(staffs);
	}

	/**
	 * get detail staff
	 * 
	 * @author tamnc
	 */
	@GetMapping(PathUtils.STAFF_GET_DETAIL)
	public ResponseEntity<APIResponse> getDetail(@PathVariable("staffId") Long staffId) {
		Staff staff = staffService.findById(staffId)
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_STAFF_NOT_EXIST));
		return ResponseUtil.responseSuccess(staff);
	}

	/**
	 * update staff
	 * 
	 * @author tamnc
	 */
	@PutMapping(PathUtils.STAFF_UPDATE)
	public ResponseEntity<APIResponse> staffUpdate(@Validated @RequestBody StaffUpdateRequest staffUpdateRequest) {

		Staff staff = staffService.findById(staffUpdateRequest.getId())
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_STAFF_NOT_EXIST));

		if (!staffUpdateRequest.getStaffCode().equals(staff.getStaffCode())) {
			Optional<Staff> staffOptional = staffService.findByStaffCode(staffUpdateRequest.getStaffCode());
			if (staffOptional.isPresent()) {
				throw new ApplicationException(APIStatus.ERR_CODE_STAFF_ALREADY_EXISTS);
			}
		}
		// trans request to entity
		staff = modelMapper.map(staffUpdateRequest, Staff.class);

		// update staff
		staffService.update(staff);

		return ResponseUtil.responseSuccess(APIStatus.SUCCESS_UPDATE);
	}

	/**
	 * create staff
	 * 
	 * @author tamnc
	 */
	@PostMapping(PathUtils.STAFF_CREATE)
	public ResponseEntity<APIResponse> staffCreate(@Validated @RequestBody StaffCreateRequest staffCreateRequest) {

		Optional<Staff> staffOptional = staffService.findByStaffCode(staffCreateRequest.getStaffCode());
		if (staffOptional.isPresent()) {
			throw new ApplicationException(APIStatus.ERR_CODE_STAFF_ALREADY_EXISTS);
		} else {
			Staff createStaff = modelMapper.map(staffCreateRequest, Staff.class);
			staffService.create(createStaff);
			return ResponseUtil.responseSuccess(APIStatus.SUCCESS_CREATE);
		}
	}

	/**
	 * delete staff
	 * 
	 * @author tamnc
	 */
	@DeleteMapping(PathUtils.STAFF_DELETE)
	public ResponseEntity<APIResponse> staffDelete(@RequestBody DeleteRequest deleteRequest) {

		List<Staff> staffs = staffService.findAllByIds(deleteRequest.getIds());

		if (staffs != null && !staffs.isEmpty()) {
			staffService.deleteAll(Staff.class , staffs);
			return ResponseUtil.responseSuccess(APIStatus.SUCCESS_DELETE);
		} else {
			throw new ApplicationException(APIStatus.ERR_ID_STAFF_NOT_EXIST);
		}
	}
}
