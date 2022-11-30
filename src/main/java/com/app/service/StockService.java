package com.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.app.entity.Stock;
import com.app.request.model.StockPagingSearchSortModel;

/**
 * @author tamnc
 *
 */
public interface StockService extends BaseService<Stock>{

	Page<Stock> findAll(StockPagingSearchSortModel cpssm);

	Optional<Stock> findByCode(String code);

//	Optional<Stock> findByStockId(Long stockId);

	void update(Stock instance);

	Stock create(Stock instance);

//	void delete(Stock stock);
//
//	void deleteAll(List<Stock> stocks);
//
//	List<Stock> findAllByStockId(List<Long> stockIds);

}
