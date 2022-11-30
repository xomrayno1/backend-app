package com.app.entity;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Cache(region = "saleOrderDetailCache",usage = CacheConcurrencyStrategy.READ_WRITE)
public class SaleOrderDetail extends AuditingEntityListener implements Serializable{

	private static final long serialVersionUID = 7370374679535279018L;
	
	@Id
	@SequenceGenerator(sequenceName = "sale_order_detail_seq", initialValue = 1, name = "sale_order_detail_generator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_order_detail_generator")
	@Column(name = "sale_order_detail_id", length = 20)
	private Long id;
	@Column(name = "customer_id", length = 20)
	private Long productId;
	@Column(name = "quantity", length = 5)
	private Integer quantity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_order_id")
	private SaleOrder saleOrder;
	@Column(name = "amount", columnDefinition = "decimal(11,2)")
	private BigDecimal amount;
	@Column(name = "price", columnDefinition = "decimal(11,2)")
	private BigDecimal price;
	@Column(name = "discount", columnDefinition = "decimal(11,2)")
	private BigDecimal discount;
	@Column(name = "total_amount", columnDefinition = "decimal(11,2)")
	private BigDecimal totalAmount;
	@Column(name = "stock_id", length = 20)
	private Long stockId; 
//	@Column(name = "is_free", length = 1)
//	private int isFree = 0;

}
