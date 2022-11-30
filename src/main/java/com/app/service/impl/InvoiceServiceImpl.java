package com.app.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.app.entity.Invoice;
import com.app.service.InvoiceService;

@Service
public class InvoiceServiceImpl extends BaseServiceImpl<Invoice> implements InvoiceService {

	public InvoiceServiceImpl(JpaRepository<Invoice, Long> jpaRepository) {
		super(jpaRepository);
	}

}
