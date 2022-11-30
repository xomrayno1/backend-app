package com.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.app.entity.Customer;
import com.app.request.model.CustomerPagingSearchSortModel;

/**
 * @author tamnc
 *
 */
public interface CustomerService  extends BaseService<Customer>{

	Page<Customer> findAll(CustomerPagingSearchSortModel cpssm);

	Optional<Customer> findByCustomerCode(String customerCode);

	Optional<Customer> findByEmail(String email);

//	Optional<Customer> findByCustomerId(Long customerId);

	void update(Customer instance);

	Customer create(Customer instance);

//	void delete(Customer customer);
//
//	void deleteAll(List<Customer> customers);
//
//	List<Customer> findAllByCustomerId(List<Long> customerIds);

}
