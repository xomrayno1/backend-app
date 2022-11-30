package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.ProductInStock;

@Repository
public interface ProductInStockRepository extends JpaRepository<ProductInStock, Long>{
	
	Optional<ProductInStock> findByProductIdAndStockIdAndStatus(Long productId, Long stockId, int status);

}
