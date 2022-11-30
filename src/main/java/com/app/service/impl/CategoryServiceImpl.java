package com.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.app.entity.Category;
import com.app.model.enumtype.Status;
import com.app.model.specification.CategorySpecification;
import com.app.repository.CategoryRepository;
import com.app.request.model.CategoryPagingSearchSortModel;
import com.app.service.CategoryService;

/**
 * @author tamnc
 *
 */
@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository, JpaRepository<Category, Long> jpaRepository) {
		super(jpaRepository);
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Page<Category> findAll(CategoryPagingSearchSortModel spssm) {
		return categoryRepository.findAll(new CategorySpecification(spssm.getSearchKey()),
				PageRequest.of(spssm.getPageNumber() - 1, spssm.getPageSize()));
	}

	@Override
	public Optional<Category> findByCode(String code) {
		return categoryRepository.findByCategoryCodeAndStatus(code, Status.ACTIVE.getValue());
	}
//
//	@Override
//	public Optional<Category> findByCategoryId(Long categoryId) {
//		return categoryRepository.findById(categoryId);
//	}

	@Override
	public void update(Category instance) {
		categoryRepository.save(instance);
	}

	@Override
	public Category create(Category instance) {
		instance.setStatus(Status.ACTIVE.getValue());
		return categoryRepository.save(instance);
	}

//	@Override
//	public void delete(Category instance) {
//		instance.setStatus(Status.IN_ACTIVE.getValue());
//		categoryRepository.save(instance);
//	}
//
//	@Override
//	public void deleteAll(List<Category> suppliers) {
//		List<Category> deleteList = suppliers.stream().map(item -> {
//			item.setStatus(Status.IN_ACTIVE.getValue());
//			return item;
//		}).toList();
//		categoryRepository.saveAll(deleteList);
//	}

//	@Override
//	public List<Category> findAllByCategoryIds(List<Long> supplierIds) {
//		return categoryRepository.findAllById(supplierIds);
//	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findByStatus(Status.ACTIVE.getValue());
	}

}
