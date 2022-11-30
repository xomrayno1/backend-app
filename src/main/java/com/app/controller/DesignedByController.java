package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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

import com.app.entity.DesignedBy;
import com.app.exception.ApplicationException;
import com.app.request.DeleteRequest;
import com.app.request.DesignedByCreateRequest;
import com.app.request.DesignedByUpdateRequest;
import com.app.response.APIResponse;
import com.app.response.APIStatus;
import com.app.service.DesignedByService;
import com.app.utils.PathUtils;
import com.app.utils.ResponseUtil;

/**
 * @author tamnc
 *
 */
@RestController
@RequestMapping(PathUtils.DESIGNED_BY_API)
public class DesignedByController {
	
	private final DesignedByService designedByService;
	
	private final ModelMapper modelMapper;
 
	public DesignedByController(DesignedByService designedByService, ModelMapper modelMapper) {
		this.designedByService = designedByService;
		this.modelMapper = modelMapper;
	}

//	/**
//	 * paging , search designedBy by name ...
//	 * 
//	 * @author tamnc
//	 */
//	@PostMapping(PathUtils.DESIGNED_BY_GET_LIST_PAGING_SEARCH_SORT)
//	public ResponseEntity<APIResponse> getListPagingSearchSort(@RequestBody DesignedByPagingSearchSortModel ppss) {
//		Page<DesignedBy> designedBys = designedByService.findAll(ppss);
//		return ResponseUtil.responseSuccess(designedBys);
//	}

	/**
	 * get detail designedBy
	 * 
	 * @author tamnc
	 */
	@GetMapping(PathUtils.DESIGNED_BY_GET_DETAIL)
	public ResponseEntity<APIResponse> getDetail(@PathVariable("designedById") Long designedById) {
		DesignedBy designedBy = designedByService.findById(designedById)
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_DESIGNED_BY_NOT_EXIST));
		return ResponseUtil.responseSuccess(designedBy);
	}

	/**
	 * update designedBy
	 * 
	 * @author tamnc
	 */
	@PutMapping(PathUtils.DESIGNED_BY_UPDATE)
	public ResponseEntity<APIResponse> designedByUpdate(
			@Validated @RequestBody DesignedByUpdateRequest designedByUpdateRequest) {

		DesignedBy designedBy = designedByService.findById(designedByUpdateRequest.getId())
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_DESIGNED_BY_NOT_EXIST));

		if (!designedByUpdateRequest.getCode().equals(designedBy.getCode())) {
			Optional<DesignedBy> supOptional = designedByService.findByCode(designedByUpdateRequest.getCode());
			if (supOptional.isPresent()) {
				throw new ApplicationException(APIStatus.ERR_CODE_DESIGNED_BY_ALREADY_EXISTS);
			}
		}
		// trans request to entity
		designedBy = modelMapper.map(designedByUpdateRequest, DesignedBy.class);

		// update designedBy
		designedByService.update(designedBy);

		return ResponseUtil.responseSuccess(APIStatus.SUCCESS_UPDATE);
	}

	/**
	 * create designedBy
	 * 
	 * @author tamnc
	 */
	@PostMapping(PathUtils.DESIGNED_BY_CREATE)
	public ResponseEntity<APIResponse> cusCreate(@Validated @RequestBody DesignedByCreateRequest designedByCreateRequest) {
		
		Optional<DesignedBy> supOptional = designedByService.findByCode(designedByCreateRequest.getCode());
		if (supOptional.isPresent()) {
			throw new ApplicationException(APIStatus.ERR_CODE_DESIGNED_BY_ALREADY_EXISTS);
		} else {
			DesignedBy createDesignedBy = modelMapper.map(designedByCreateRequest, DesignedBy.class);
			designedByService.create(createDesignedBy);
			return ResponseUtil.responseSuccess(APIStatus.SUCCESS_CREATE);
		}
	}

	/**
	 * delete designedBy
	 * 
	 * @author tamnc
	 */
	@DeleteMapping(PathUtils.DESIGNED_BY_DELETE)
	public ResponseEntity<APIResponse> cusDelete(@RequestBody DeleteRequest deleteRequest) {

		List<DesignedBy> designedBys = designedByService.findAllByIds(deleteRequest.getIds());
		 
		if (designedBys != null && !designedBys.isEmpty()) {
			designedByService.deleteAll(DesignedBy.class, designedBys);
			return ResponseUtil.responseSuccess(APIStatus.SUCCESS_DELETE);
		} else {
			throw new ApplicationException(APIStatus.ERR_ID_DESIGNED_BY_NOT_EXIST);
		}
	}
	
	@GetMapping(PathUtils.DESIGNED_BY_GET_ALL)
	public ResponseEntity<APIResponse> getAll() {
		List<DesignedBy> designedBies = designedByService.findAll();
		return ResponseUtil.responseSuccess(designedBies);
	}
}
