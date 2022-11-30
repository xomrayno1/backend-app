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

import com.app.entity.Stock;
import com.app.exception.ApplicationException;
import com.app.request.DeleteRequest;
import com.app.request.StockCreateRequest;
import com.app.request.StockUpdateRequest;
import com.app.request.model.StockPagingSearchSortModel;
import com.app.response.APIResponse;
import com.app.response.APIStatus;
import com.app.service.StockService;
import com.app.utils.PathUtils;
import com.app.utils.ResponseUtil;

@RestController
@RequestMapping(PathUtils.STOCK_API)
public class StockController {
	private final StockService stockService;
	
	private final ModelMapper modelMapper;
 
	public StockController(StockService stockService, ModelMapper modelMapper) {
		this.stockService = stockService;
		this.modelMapper = modelMapper;
	}

	/**
	 * paging , search stock by name ...
	 * 
	 * @author tamnc
	 */
	@PostMapping(PathUtils.STOCK_GET_LIST_PAGING_SEARCH_SORT)
	public ResponseEntity<APIResponse> getListPagingSearchSort(@RequestBody StockPagingSearchSortModel ppss) {
		Page<Stock> stocks = stockService.findAll(ppss);
		return ResponseUtil.responseSuccess(stocks);
	}

	/**
	 * get detail stock
	 * 
	 * @author tamnc
	 */
	@GetMapping(PathUtils.STOCK_GET_DETAIL)
	public ResponseEntity<APIResponse> getDetail(@PathVariable("stockId") Long stockId) {
		Stock stock = stockService.findById(stockId)
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_STOCK_NOT_EXIST));
		return ResponseUtil.responseSuccess(stock);
	}

	/**
	 * update stock
	 * 
	 * @author tamnc
	 */
	@PutMapping(PathUtils.STOCK_UPDATE)
	public ResponseEntity<APIResponse> stockUpdate(
			@Validated @RequestBody StockUpdateRequest stockUpdateRequest) {

		Stock stock = stockService.findById(stockUpdateRequest.getId())
				.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_STOCK_NOT_EXIST));

		if (!stockUpdateRequest.getCode().equals(stock.getCode())) {
			Optional<Stock> stockOptional = stockService.findByCode(stockUpdateRequest.getCode());
			if (stockOptional.isPresent()) {
				throw new ApplicationException(APIStatus.ERR_CODE_STOCK_ALREADY_EXISTS);
			}
		}
		// trans request to entity
		stock = modelMapper.map(stockUpdateRequest, Stock.class);

		// update stock
		stockService.update(stock);

		return ResponseUtil.responseSuccess(APIStatus.SUCCESS_UPDATE);
	}

	/**
	 * create stock
	 * 
	 * @author tamnc
	 */
	@PostMapping(PathUtils.STOCK_CREATE)
	public ResponseEntity<APIResponse> stockCreate(@Validated @RequestBody StockCreateRequest stockCreateRequest) {
		
		Optional<Stock> stockOptional = stockService.findByCode(stockCreateRequest.getCode());
		if (stockOptional.isPresent()) {
			throw new ApplicationException(APIStatus.ERR_CODE_STOCK_ALREADY_EXISTS);
		} else {
			Stock createStock = modelMapper.map(stockCreateRequest, Stock.class);
			stockService.create(createStock);
			return ResponseUtil.responseSuccess(APIStatus.SUCCESS_CREATE);
		}
	}

	/**
	 * delete stock
	 * 
	 * @author tamnc
	 */
	@DeleteMapping(PathUtils.STOCK_DELETE)
	public ResponseEntity<APIResponse> stockDelete(@RequestBody DeleteRequest deleteRequest) {

		List<Stock> stocks = stockService.findAllByIds(deleteRequest.getIds());
		 
		if (stocks != null && !stocks.isEmpty()) {
			stockService.deleteAll(Stock.class ,stocks);
			return ResponseUtil.responseSuccess(APIStatus.SUCCESS_DELETE);
		} else {
			throw new ApplicationException(APIStatus.ERR_ID_STOCK_NOT_EXIST);
		}
	}
}
