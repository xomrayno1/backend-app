package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.app.entity.SaleOrder;
import com.app.request.SaleOrderCreateRequest;
import com.app.request.SaleOrderUpdateRequest;
import com.app.request.model.SaleOrderPagingSearchSortModel;

/**
 * @author tamnc
 *
 */
public interface SaleOrderService {

	Page<SaleOrder> findAll(SaleOrderPagingSearchSortModel cpssm);

	Optional<SaleOrder> findBySaleOrderId(Long saleOrderId);

	void update(SaleOrder saleOrder, SaleOrderUpdateRequest saleOrderUpdateRequest);

	SaleOrder create(SaleOrderCreateRequest saleOrderCreateRequest);

	void delete(SaleOrder stock);

	void deleteAll(List<SaleOrder> stocks);

	List<SaleOrder> findAllBySaleOrderId(List<Long> saleOrderIds);

}
