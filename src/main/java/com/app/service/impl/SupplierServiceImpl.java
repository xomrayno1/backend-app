package com.app.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.app.entity.Supplier;
import com.app.model.enumtype.Status;
import com.app.model.specification.SupplierSpecification;
import com.app.repository.SupplierRepository;
import com.app.request.model.SupplierPagingSearchSortModel;
import com.app.service.SupplierService;

/**
 * @author tamnc
 *
 */
@Service
public class SupplierServiceImpl extends BaseServiceImpl<Supplier> implements SupplierService{
 
	private final SupplierRepository supplierRepository;
	 
	public SupplierServiceImpl(JpaRepository<Supplier, Long> jpaRepository, SupplierRepository supplierRepository) {
		super(jpaRepository);
		this.supplierRepository = supplierRepository;
	}

	@Override
	public Page<Supplier> findAll(SupplierPagingSearchSortModel spssm) {
		return supplierRepository.findAll(new SupplierSpecification(spssm.getSearchKey()),
				PageRequest.of(spssm.getPageNumber() - 1, spssm.getPageSize()));
	}

	@Override
	public Optional<Supplier> findByCode(String code) {
		return supplierRepository.findByCodeAndStatus(code, Status.ACTIVE.getValue());
	}
	
	@Override
	public void update(Supplier instance) {
		supplierRepository.save(instance);
	}

	@Override
	public Supplier create(Supplier instance) {
		instance.setStatus(Status.ACTIVE.getValue());
		return supplierRepository.save(instance);
	}

//	@Override
//	public Optional<Supplier> findBySupplierId(Long supplierId) {
//		return supplierRepository.findById(supplierId);
//	}

//	@Override
//	public void delete(Supplier supplier) {
//		supplier.setStatus(Status.IN_ACTIVE.getValue());
//		supplierRepository.save(supplier);
//	}
//
//	@Override
//	public void deleteAll(List<Supplier> suppliers) {
//		List<Supplier> deleteList = suppliers.stream().map(item -> {
//			item.setStatus(Status.IN_ACTIVE.getValue());
//			return item;
//		}).toList();
//		supplierRepository.saveAll(deleteList);
//	}
//
//	@Override
//	public List<Supplier> findAllBySupplierIds(List<Long> supplierIds) {
//		return supplierRepository.findAllById(supplierIds);
//	}

}
