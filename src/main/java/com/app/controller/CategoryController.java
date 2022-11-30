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

import com.app.entity.Category;
import com.app.exception.ApplicationException;
import com.app.request.CategoryCreateRequest;
import com.app.request.CategoryUpdateRequest;
import com.app.request.DeleteRequest;
import com.app.request.model.CategoryPagingSearchSortModel;
import com.app.response.APIResponse;
import com.app.response.APIStatus;
import com.app.service.CategoryService;
import com.app.utils.PathUtils;
import com.app.utils.ResponseUtil;

/**
 * @author tamnc
 *
 */
@RestController
@RequestMapping(PathUtils.CATEGORY_API)
public class CategoryController {

	private final CategoryService categoryService;

	private final ModelMapper modelMapper;

	public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
		this.categoryService = categoryService;
		this.modelMapper = modelMapper;
	}

	/**
	 * paging , search category by name ...
	 * 
	 * @author tamnc
	 */
	@PostMapping(PathUtils.CATEGORY_GET_LIST_PAGING_SEARCH_SORT)
	public ResponseEntity<APIResponse> getListPagingSearchSort(@RequestBody CategoryPagingSearchSortModel ppss) {
		Page<Category> categorys = categoryService.findAll(ppss);
		return ResponseUtil.responseSuccess(categorys);
	}

	/**
	 * get detail category
	 * 
	 * @author tamnc
	 */
	@GetMapping(PathUtils.CATEGORY_GET_DETAIL)
	public ResponseEntity<APIResponse> getDetail(@PathVariable("categoryId") Long categoryId) {
		Category category = categoryService.findById(categoryId)
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_CATEGORY_NOT_EXIST));
		return ResponseUtil.responseSuccess(category);
	}

	/**
	 * update category
	 * 
	 * @author tamnc
	 */
	@PutMapping(PathUtils.CATEGORY_UPDATE)
	public ResponseEntity<APIResponse> categoryUpdate(
			@Validated @RequestBody CategoryUpdateRequest categoryUpdateRequest) {

		Category category = categoryService.findById(categoryUpdateRequest.getId())
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_CATEGORY_NOT_EXIST));

		if (!categoryUpdateRequest.getCategoryCode().equals(category.getCategoryCode())) {
			Optional<Category> supOptional = categoryService.findByCode(categoryUpdateRequest.getCategoryCode());
			if (supOptional.isPresent()) {
				throw new ApplicationException(APIStatus.ERR_CODE_CATEGORY_ALREADY_EXISTS);
			}
		}
		// trans request to entity
		category = modelMapper.map(categoryUpdateRequest, Category.class);

		// update category
		categoryService.update(category);

		return ResponseUtil.responseSuccess(APIStatus.SUCCESS_UPDATE);
	}

	/**
	 * create category
	 * 
	 * @author tamnc
	 */
	@PostMapping(PathUtils.CATEGORY_CREATE)
	public ResponseEntity<APIResponse> cusCreate(@Validated @RequestBody CategoryCreateRequest categoryCreateRequest) {

		Optional<Category> supOptional = categoryService.findByCode(categoryCreateRequest.getCategoryCode());
		if (supOptional.isPresent()) {
			throw new ApplicationException(APIStatus.ERR_CODE_CATEGORY_ALREADY_EXISTS);
		} else {
			Category createCategory = modelMapper.map(categoryCreateRequest, Category.class);
			categoryService.create(createCategory);
			return ResponseUtil.responseSuccess(APIStatus.SUCCESS_CREATE);
		}
	}

	/**
	 * delete category
	 * 
	 * @author tamnc
	 */
	@DeleteMapping(PathUtils.CATEGORY_DELETE)
	public ResponseEntity<APIResponse> cusDelete(@RequestBody DeleteRequest deleteRequest) {

		List<Category> categories = categoryService.findAllByIds(deleteRequest.getIds());

		if (!categories.isEmpty()) {
			categoryService.deleteAll(Category.class, categories);
			return ResponseUtil.responseSuccess(APIStatus.SUCCESS_DELETE);
		} else {
			throw new ApplicationException(APIStatus.ERR_ID_CATEGORY_NOT_EXIST);
		}
	}

	/**
	 * get all
	 * 
	 * @author tamnc
	 */
	@GetMapping(PathUtils.CATEGORY_GET_All)
	public ResponseEntity<APIResponse> getAll() {
		List<Category> categories = categoryService.findAll();

		return ResponseUtil.responseSuccess(categories);
	}

}
