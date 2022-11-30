package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.app.entity.Product;
import com.app.request.model.ProductPagingSearchSortModel;

public interface ProductService extends BaseService<Product>{

	Page<Product> findAll(ProductPagingSearchSortModel dpssm);
	
	List<Product> findAll();

	Optional<Product> findByCode(String proCode);
//
	Optional<Product> findById(Long proId);

	Product update(Product instance);

	Product create(Product instance);

	void delete(Product product);

	void deleteAll(List<Product> products);

	List<Product> findAllById(List<Long> productIds);
	
}
