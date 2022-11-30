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

import com.app.entity.Product;
import com.app.exception.ApplicationException;
import com.app.request.DeleteRequest;
import com.app.request.ProductCreateRequest;
import com.app.request.ProductUpdateRequest;
import com.app.request.model.ProductPagingSearchSortModel;
import com.app.response.APIResponse;
import com.app.response.APIStatus;
import com.app.service.DesignedByService;
import com.app.service.ProductService;
import com.app.utils.PathUtils;
import com.app.utils.ResponseUtil;

@RestController
@RequestMapping(PathUtils.PRODUCT_API)
public class ProductController {

	private final ModelMapper modelMapper;

	private final ProductService productService;

	private final DesignedByService designedByService;

	public ProductController(ModelMapper modelMapper, ProductService productService,
			DesignedByService designedByService) {
		this.modelMapper = modelMapper;
		this.productService = productService;
		this.designedByService = designedByService;
	}

	/**
	 * paging , search product by name ...
	 * 
	 * @author tamnc
	 */
	@PostMapping(PathUtils.PRODUCT_GET_LIST_PAGING_SEARCH_SORT)
	public ResponseEntity<APIResponse> getListPagingSearchSort(@RequestBody ProductPagingSearchSortModel ppss) {
		Page<Product> products = productService.findAll(ppss);
		return ResponseUtil.responseSuccess(products);
	}

	/**
	 * get detail product
	 * 
	 * @author tamnc
	 */
	@GetMapping(PathUtils.PRODUCT_GET_DETAIL)
	public ResponseEntity<APIResponse> getDetail(@PathVariable("productId") Long productId) {
		Product product = productService.findById(productId)
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_PRODUCT_NOT_EXIST));
		return ResponseUtil.responseSuccess(product);
	}

	/**
	 * update product
	 * 
	 * @author tamnc
	 */
	@PutMapping(PathUtils.PRODUCT_UPDATE)
	public ResponseEntity<APIResponse> productUpdate(
			@Validated @RequestBody ProductUpdateRequest updateProductRequest) {

		// validate
		Product product = productService.findById(updateProductRequest.getId())
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_PRODUCT_NOT_EXIST));

		designedByService.findById(updateProductRequest.getDesignedById())
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_DESIGNED_BY_NOT_EXIST));

		if (!updateProductRequest.getProductCode().equals(product.getProductCode())) {
			Optional<Product> supOptional = productService.findByCode(updateProductRequest.getProductCode());
			if (supOptional.isPresent()) {
				throw new ApplicationException(APIStatus.ERR_CODE_PRODUCT_ALREADY_EXISTS);
			}
		}
		// trans request to entity
		product = modelMapper.map(updateProductRequest, Product.class);

		// update product
		productService.update(product);

		return ResponseUtil.responseSuccess(APIStatus.SUCCESS_UPDATE);
	}

	/**
	 * create product
	 * 
	 * @author tamnc
	 */
	@PostMapping(PathUtils.PRODUCT_CREATE)
	public ResponseEntity<APIResponse> proCreate(@Validated @RequestBody ProductCreateRequest createProductRequest) {
		Optional<Product> supOptional = productService.findByCode(createProductRequest.getProductCode());

		designedByService.findById(createProductRequest.getDesignedById())
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_DESIGNED_BY_NOT_EXIST));

		if (supOptional.isPresent()) {
			throw new ApplicationException(APIStatus.ERR_CODE_PRODUCT_ALREADY_EXISTS);
		} else {
			Product product = modelMapper.map(createProductRequest, Product.class);

			productService.create(product);
			return ResponseUtil.responseSuccess(product);
		}
	}

	/**
	 * delete product
	 * 
	 * @author tamnc
	 */
	@DeleteMapping(PathUtils.PRODUCT_DELETE)
	public ResponseEntity<APIResponse> cusDelete(@RequestBody DeleteRequest deleteRequest) {
		List<Product> products = productService.findAllByIds(deleteRequest.getIds());

		if (products != null && !products.isEmpty()) {
			productService.deleteAll(Product.class, products);
			return ResponseUtil.responseSuccess(APIStatus.SUCCESS_DELETE);
		} else {
			throw new ApplicationException(APIStatus.ERR_ID_PRODUCT_NOT_EXIST);
		}
	}

	/**
	 * get all product
	 * 
	 * @author tamnc
	 */
	@GetMapping(PathUtils.PRODUCT_GET_ALL)
	public ResponseEntity<APIResponse> getAll() {
		List<Product> products = productService.findAll();
		return ResponseUtil.responseSuccess(products);
	}

}
