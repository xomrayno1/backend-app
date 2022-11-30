package com.app.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.app.entity.Customer;
import com.app.model.enumtype.Status;
import com.app.model.specification.CustomerSpecification;
import com.app.repository.CustomerRepository;
import com.app.request.model.CustomerPagingSearchSortModel;
import com.app.service.CustomerService;

/**
 * @author tamnc
 *
 */
@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService{
 
	private final CustomerRepository cusRepo;
   
	public CustomerServiceImpl(JpaRepository<Customer, Long> jpaRepository, CustomerRepository cusRepo) {
		super(jpaRepository);
		this.cusRepo = cusRepo;
	}

	@Override
	public Page<Customer> findAll(CustomerPagingSearchSortModel cpssm) {
		return cusRepo.findAll(new CustomerSpecification(cpssm.getSearchKey(), cpssm.getPhone()),
				PageRequest.of(cpssm.getPageNumber() - 1, cpssm.getPageSize()));
	}

	@Override
	public Optional<Customer> findByCustomerCode(String customerCode) {
		return cusRepo.findByCustomerCodeAndStatus(customerCode, Status.ACTIVE.getValue());
	}

	@Override
	public Optional<Customer> findByEmail(String email) {
		return cusRepo.findByEmailAndStatus(email, Status.ACTIVE.getValue());
	}

//	@Override
//	public Optional<Customer> findByCustomerId(Long customerId) {
//		return cusRepo.findById(customerId);
//	}

	@Override
	public void update(Customer instance) {
		cusRepo.save(instance);
	}

	@Override
	public Customer create(Customer instance) {
		instance.setStatus(Status.ACTIVE.getValue());
		return cusRepo.save(instance);
	}

//	@Override
//	public void delete(Customer customer) {
//		customer.setStatus(Status.IN_ACTIVE.getValue());
//		cusRepo.save(customer);
//	}
//
//	@Override
//	public void deleteAll(List<Customer> customers) {
//		List<Customer> deleteCustomers = customers.stream().map(customer -> {
//			customer.setStatus(Status.IN_ACTIVE.getValue());
//			return customer;
//		}).toList();
//		cusRepo.saveAll(deleteCustomers);
//	}

//	@Override
//	public List<Customer> findAllByCustomerId(List<Long> customerIds) {
//		return cusRepo.findAllById(customerIds);
//	}

}
