package com.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.entity.Product;
import com.app.entity.ProductInStock;
import com.app.entity.SaleOrder;
import com.app.entity.SaleOrderDetail;
import com.app.entity.Stock;
import com.app.exception.ApplicationException;
import com.app.model.enumtype.ApprovedStep;
import com.app.model.enumtype.Status;
import com.app.model.specification.SaleOrderSpecification;
import com.app.repository.ProductInStockRepository;
import com.app.repository.ProductRepository;
import com.app.repository.SaleOrderDetailRepository;
import com.app.repository.SaleOrderRepository;
import com.app.repository.StockRepository;
import com.app.request.SaleOrderCreateRequest;
import com.app.request.SaleOrderUpdateRequest;
import com.app.request.model.SaleOrderPagingSearchSortModel;
import com.app.response.APIStatus;
import com.app.service.SaleOrderService;

/**
 * @author tamnc
 *
 */
@Service
public class SaleOrderServiceImpl implements SaleOrderService{
 
	private final SaleOrderRepository saleOrderRepo;
 
	private final SaleOrderDetailRepository saleOrderDetailRepo;
 
	private final ProductInStockRepository productInStockRepo;
 
	private final ProductRepository productRepo;
 
	private final StockRepository stockRepo;
	
	public SaleOrderServiceImpl(SaleOrderRepository saleOrderRepo, SaleOrderDetailRepository saleOrderDetailRepo,
			ProductInStockRepository productInStockRepo, ProductRepository productRepo, StockRepository stockRepo) {
		this.saleOrderRepo = saleOrderRepo;
		this.saleOrderDetailRepo = saleOrderDetailRepo;
		this.productInStockRepo = productInStockRepo;
		this.productRepo = productRepo;
		this.stockRepo = stockRepo;
	}

	@Override
	public Page<SaleOrder> findAll(SaleOrderPagingSearchSortModel cpssm) {
		return saleOrderRepo.findAll(new SaleOrderSpecification(cpssm.getDateFrom(), cpssm.getDateTo()),
				PageRequest.of(cpssm.getPageNumber() - 1, cpssm.getPageSize()));
	}

	@Override
	public Optional<SaleOrder> findBySaleOrderId(Long saleOrderId) {
		return saleOrderRepo.findById(saleOrderId);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void update(SaleOrder saleOrder, SaleOrderUpdateRequest saleOrderUpdateRequest) {
 
		saleOrder.setAmount(saleOrderUpdateRequest.getAmount());
		saleOrder.setApprovedStep(ApprovedStep.WATTING.getValue());
		saleOrder.setCustomerId(saleOrderUpdateRequest.getCustomerId());
		saleOrder.setDiscount(saleOrderUpdateRequest.getDiscount());
		saleOrder.setOrderDate(new Date());
		saleOrder.setOrderNumber("");
		saleOrder.setTotalAmount(saleOrderUpdateRequest.getTotalAmount());		
		saleOrder.setStatus(Status.ACTIVE.getValue());
		saleOrder.setStaffId(saleOrderUpdateRequest.getStaffId());
		saleOrderRepo.save(saleOrder);
		
		List<SaleOrderDetail> createSaleOrderDetails = new ArrayList<>(
				saleOrderUpdateRequest.getSaleOrderDetails().size());

		List<ProductInStock> updateProductInStocks = new ArrayList<>(
				saleOrderUpdateRequest.getSaleOrderDetails().size());
		
		saleOrderUpdateRequest.getSaleOrderDetails().forEach(item -> {
			Product product = productRepo.findById(item.getProductId())
					.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_PRODUCT_NOT_EXIST));

			Stock stock = stockRepo.findById(item.getStockId())
					.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_STOCK_NOT_EXIST));

			ProductInStock productInStock = productInStockRepo
					.findByProductIdAndStockIdAndStatus(item.getProductId(), item.getStockId(),
							Status.ACTIVE.getValue())
					.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_STOCK_AND_PRODUCT_NOT_EXIST));

			if (productInStock.getQuantity() < item.getQuantity()) {
				throw new ApplicationException(APIStatus.ERR_ID_STOCK_AND_PRODUCT_NOT_EXIST);
			}
			// create sale order detail
			SaleOrderDetail saleOrderDetail = new SaleOrderDetail();
			saleOrderDetail.setAmount(item.getAmout());
			saleOrderDetail.setDiscount(item.getDiscount());
			saleOrderDetail.setPrice(item.getPrice());
			saleOrderDetail.setProductId(item.getProductId());
			saleOrderDetail.setQuantity(item.getQuantity());
			saleOrderDetail.setSaleOrder(saleOrder);
			saleOrderDetail.setTotalAmount(item.getTotalAmount());
			saleOrderDetail.setStockId(item.getStockId());
			createSaleOrderDetails.add(saleOrderDetail);
			// update product in stock
			productInStock.setProduct(product);
			productInStock.setQuantity(productInStock.getQuantity() - item.getQuantity());
			productInStock.setStock(stock);
			updateProductInStocks.add(productInStock);

		});

		saleOrderDetailRepo.saveAll(createSaleOrderDetails);
		productInStockRepo.saveAll(updateProductInStocks);
		 
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public SaleOrder create(SaleOrderCreateRequest saleOrderCreateRequest) {
		SaleOrder saleOrder = new SaleOrder();
		saleOrder.setAmount(saleOrderCreateRequest.getAmount());
		saleOrder.setApprovedStep(ApprovedStep.WATTING.getValue());
		saleOrder.setCustomerId(saleOrderCreateRequest.getCustomerId());
		saleOrder.setDiscount(saleOrderCreateRequest.getDiscount());
		saleOrder.setOrderDate(new Date());
		saleOrder.setOrderNumber("");
		saleOrder.setTotalAmount(saleOrderCreateRequest.getTotalAmount());		
		saleOrder.setStatus(Status.ACTIVE.getValue());
		saleOrder.setStaffId(saleOrderCreateRequest.getStaffId());
		saleOrderRepo.save(saleOrder);
		
		List<SaleOrderDetail> createSaleOrderDetails = new ArrayList<>(
				saleOrderCreateRequest.getSaleOrderDetails().size());

		List<ProductInStock> updateProductInStocks = new ArrayList<>(
				saleOrderCreateRequest.getSaleOrderDetails().size());
		
		saleOrderCreateRequest.getSaleOrderDetails().forEach(item -> {
			Product product = productRepo.findById(item.getProductId())
					.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_PRODUCT_NOT_EXIST));

			Stock stock = stockRepo.findById(item.getStockId())
					.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_STOCK_NOT_EXIST));

			ProductInStock productInStock = productInStockRepo
					.findByProductIdAndStockIdAndStatus(item.getProductId(), item.getStockId(),
							Status.ACTIVE.getValue())
					.orElseThrow(() -> new ApplicationException(APIStatus.ERR_ID_STOCK_AND_PRODUCT_NOT_EXIST));

			if (productInStock.getQuantity() < item.getQuantity()) {
				throw new ApplicationException(APIStatus.ERR_ID_STOCK_AND_PRODUCT_NOT_EXIST);
			}
			// create sale order detail
			SaleOrderDetail saleOrderDetail = new SaleOrderDetail();
			saleOrderDetail.setAmount(item.getAmout());
			saleOrderDetail.setDiscount(item.getDiscount());
			saleOrderDetail.setPrice(item.getPrice());
			saleOrderDetail.setProductId(item.getProductId());
			saleOrderDetail.setQuantity(item.getQuantity());
			saleOrderDetail.setSaleOrder(saleOrder);
			saleOrderDetail.setTotalAmount(item.getTotalAmount());
			saleOrderDetail.setStockId(item.getStockId());
			createSaleOrderDetails.add(saleOrderDetail);
			// update product in stock
			productInStock.setProduct(product);
			productInStock.setQuantity(productInStock.getQuantity() - item.getQuantity());
			productInStock.setStock(stock);
			updateProductInStocks.add(productInStock);

		});

		saleOrderDetailRepo.saveAll(createSaleOrderDetails);
		productInStockRepo.saveAll(updateProductInStocks);
		return saleOrder;
	}

	@Override
	public void delete(SaleOrder stock) {
		stock.setStatus(Status.IN_ACTIVE.getValue());
		saleOrderRepo.save(stock);
	}

	@Override
	public void deleteAll(List<SaleOrder> stocks) {
		List<SaleOrder> deleteList = stocks.stream().map(item -> {
			item.setStatus(Status.IN_ACTIVE.getValue());
			return item;
		}).collect(Collectors.toList());
		saleOrderRepo.saveAll(deleteList);
	}

	@Override
	public List<SaleOrder> findAllBySaleOrderId(List<Long> saleOrderIds) {
		return saleOrderRepo.findAllById(saleOrderIds);
	}

}
