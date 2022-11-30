package com.app.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.app.model.AbstractAuditingEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InvoiceDetail extends AbstractAuditingEntity{
	
	private static final long serialVersionUID = -3193839200695243275L;
	@Id
	@SequenceGenerator(sequenceName = "invoice_detail_seq", initialValue = 1, name = "invoice_detail_generator")
	@GeneratedValue(generator = "invoice_detail_generator", strategy = GenerationType.SEQUENCE)
	@Column(name = "invoice_detail_id", length = 20)
	private Long instance;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(name = "price", length = 12)
	private BigDecimal price;
	
	@Column(name = "quantity", length = 10)
	private Integer quantity;
	
	@Column(name = "unit_price", length = 12)
	private BigDecimal unitPrice;
	
	@Column(name = "discount", length = 12)
	private BigDecimal discount;
	
	@Column(name = "total_price", length = 15)
	private BigDecimal totalPrice;

}
