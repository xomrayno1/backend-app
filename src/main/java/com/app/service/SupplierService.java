package com.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.app.entity.Supplier;
import com.app.request.model.SupplierPagingSearchSortModel;

/**
 * @author tamnc
 *
 */
public interface SupplierService extends BaseService<Supplier>{

	Page<Supplier> findAll(SupplierPagingSearchSortModel cpssm);

	Optional<Supplier> findByCode(String code);

//	Optional<Supplier> findBySupplierId(Long supplierId);

	void update(Supplier instance);

	Supplier create(Supplier instance);

//	void delete(Supplier supplier);
//
//	void deleteAll(List<Supplier> suppliers);
//
//	List<Supplier> findAllBySupplierIds(List<Long> supplierIds);

}
