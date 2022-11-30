package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.SaleOrderDetail;

/**
 * @author tamnc
 *
 */
@Repository
public interface SaleOrderDetailRepository extends JpaRepository<SaleOrderDetail, Long>{

}
