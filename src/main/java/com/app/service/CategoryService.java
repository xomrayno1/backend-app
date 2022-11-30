package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.app.entity.Category;
import com.app.request.model.CategoryPagingSearchSortModel;

/**
 * @author tamnc
 *
 */
public interface CategoryService extends BaseService<Category> {

	Page<Category> findAll(CategoryPagingSearchSortModel cpssm);

	Optional<Category> findByCode(String code);

//	Optional<Category> findByCategoryId(Long categoryId);

	void update(Category instance);

	Category create(Category instance);

//	void delete(Category category);
//
//	void deleteAll(List<Category> categories);

//	List<Category> findAllByCategoryIds(List<Long> categoryIds);
	
	List<Category> findAll();

}
