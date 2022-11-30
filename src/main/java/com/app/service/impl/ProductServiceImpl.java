package com.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.app.entity.Product;
import com.app.model.enumtype.Status;
import com.app.model.specification.ProductSpecification;
import com.app.repository.ProductRepository;
import com.app.request.model.ProductPagingSearchSortModel;
import com.app.service.ProductService;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{
 
	private final ProductRepository proRepo;

	public ProductServiceImpl(JpaRepository<Product, Long> jpaRepository, ProductRepository proRepo) {
		super(jpaRepository);
		this.proRepo = proRepo;
	}

	@Override
	public Page<Product> findAll(ProductPagingSearchSortModel ppssm) {
		return proRepo.findAll(
				new ProductSpecification(ppssm.getSearchKey(), ppssm.getSupplierId(), ppssm.getCategoryId()),
				PageRequest.of(ppssm.getPageNumber() - 1, ppssm.getPageSize()));
	}

	@Override
	@Cacheable(value = "products", key = "#proCode")
	public Optional<Product> findByCode(String proCode) {
		return proRepo.findByProductCodeAndStatus(proCode, Status.ACTIVE.getValue());
	}

	@Override
	@Cacheable(value = "products", key = "#proId")
	public Optional<Product> findById(Long proId) {
		return proRepo.findById(proId);
	}

	@Override
	@CachePut(value = "products", key = "#instance.id")
	public Product update(Product instance) {
		return proRepo.save(instance);
	}

	@Override
	public Product create(Product instance) {
		instance.setStatus(Status.ACTIVE.getValue());
		return proRepo.save(instance);
	}

	@Override
	@CacheEvict(value = "products", key = "#pro.id")
	public void delete(Product pro) {
		pro.setStatus(Status.IN_ACTIVE.getValue());
		proRepo.save(pro);
	}

	@Override
	@CacheEvict(value = "products", allEntries = true)
	public void deleteAll(List<Product> pros) {
		List<Product> listDelete = pros.stream().map(item -> {
			item.setStatus(Status.IN_ACTIVE.getValue());
			return item;
		}).collect(Collectors.toList());
		proRepo.saveAll(listDelete);
	}

	@Override
	public List<Product> findAllById(List<Long> proIds) {
		return proRepo.findAllById(proIds);
	}

	@Override
	public List<Product> findAll() {
		return proRepo.findByStatus(Status.ACTIVE.getValue());
	}

}
