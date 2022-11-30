package com.app.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.app.model.AbstractAuditingEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Invoice extends AbstractAuditingEntity{
	
	private static final long serialVersionUID = 6763380662055975965L;
	
	@Id
	@SequenceGenerator(sequenceName = "invoice_seq", initialValue = 1, name = "invoice_generator")
	@GeneratedValue(generator = "invoice_generator", strategy = GenerationType.SEQUENCE)
	@Column(name = "invoice_id", length = 20)
	private Long id;
	
	@Column(name = "object_type", length = 1)
	private int objectType;
	
	@Column(name = "object_id", length = 20)
	private int objectId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(name = "status", length = 1)
	private int status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_id")
	private Stock stock;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staff_id")
	private Staff staff;
	
	@Column(name = "total_price", length = 15)
	private BigDecimal totalPrice;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
}
