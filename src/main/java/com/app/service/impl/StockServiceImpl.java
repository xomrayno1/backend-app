package com.app.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.app.entity.Stock;
import com.app.model.enumtype.Status;
import com.app.model.specification.StockSpecification;
import com.app.repository.StockRepository;
import com.app.request.model.StockPagingSearchSortModel;
import com.app.service.StockService;

/**
 * @author tamnc
 *
 */
@Service
public class StockServiceImpl extends BaseServiceImpl<Stock> implements StockService{
 
	private final StockRepository stockRepo;
	
	public StockServiceImpl(JpaRepository<Stock, Long> jpaRepository, StockRepository stockRepo) {
		super(jpaRepository);
		this.stockRepo = stockRepo;
	}

	@Override
	public Page<Stock> findAll(StockPagingSearchSortModel cpssm) {
		return stockRepo.findAll(new StockSpecification(cpssm.getSearchKey()),
				PageRequest.of(cpssm.getPageNumber() - 1, cpssm.getPageSize()));
	}

	@Override
	public Optional<Stock> findByCode(String code) {
		return stockRepo.findByCodeAndStatus(code, Status.ACTIVE.getValue());
	}

//	@Override
//	public Optional<Stock> findByStockId(Long stockId) {
//		return stockRepo.findById(stockId);
//	}

	@Override
	public void update(Stock instance) {
		stockRepo.save(instance);
	}

	@Override
	public Stock create(Stock instance) {
		instance.setStatus(Status.ACTIVE.getValue());
		return stockRepo.save(instance);
	}

//	@Override
//	public void delete(Stock stock) {
//		stock.setStatus(Status.IN_ACTIVE.getValue());
//		stockRepo.save(stock);
//	}
//
//	@Override
//	public void deleteAll(List<Stock> stocks) {
//		List<Stock> deleteList = stocks.stream().map(item -> {
//			item.setStatus(Status.IN_ACTIVE.getValue());
//			return item;
//		}).collect(Collectors.toList());
//		stockRepo.saveAll(deleteList);
//	}
//
//	@Override
//	public List<Stock> findAllByStockId(List<Long> stockIds) {
//		return stockRepo.findAllById(stockIds);
//	}

}
