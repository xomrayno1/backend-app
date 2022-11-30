package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.app.entity.Customer;

/**
 * @author tamnc
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer>{
	
	Optional<Customer> findByCustomerCodeAndStatus(String customerCode, int status);
	
	Optional<Customer> findByEmailAndStatus(String email, int status);

}
