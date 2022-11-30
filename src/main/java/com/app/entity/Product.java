package com.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.app.model.AbstractAuditingEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product")
@Cache(region = "productCache",usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product extends AbstractAuditingEntity implements Serializable{
	
	private static final long serialVersionUID = -1082753712699236938L;
	
	@Id
	@SequenceGenerator(sequenceName = "product_seq", initialValue = 1, name = "product_generator")
	@GeneratedValue(generator = "product_generator", strategy = GenerationType.SEQUENCE)
	@Column(name = "product_id", length = 20)
	private Long id;
	@Column(name = "product_name", length = 255)
	private String productName;
	@Column(name = "product_code", length = 32)
	private String productCode;
	@Column(name = "product_price", columnDefinition = "decimal(11,2)")
	private BigDecimal productPrice;
	@Column(name = "category_id", length = 20)
	private Long categoryId;
	@Column(name = "designed_by_id", length = 20)
	private Long designedById;
	@Column(name = "product_note", columnDefinition = "text")
	private String productNote;
	@Column(length = 1, nullable = false)
	private int status;
}
