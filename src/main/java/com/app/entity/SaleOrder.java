package com.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.app.model.AbstractAuditingEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tamnc
 *
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Cache(region = "saleOrderCache",usage = CacheConcurrencyStrategy.READ_WRITE)
public class SaleOrder extends AbstractAuditingEntity implements Serializable{
	
	private static final long serialVersionUID = -4741827952896012635L;
	
	@Id
	@SequenceGenerator(sequenceName = "sale_order_seq", initialValue = 1, name = "sale_order_generator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_order_generator")
	@Column(name = "sale_order_id", length = 20)
	private Long id;
	@Column(name = "customer_id", length = 20)
	private Long customerId;
	@Column(name = "amount", columnDefinition = "decimal(11,2)")
	private BigDecimal amount;
	@Column(name = "discount", columnDefinition = "decimal(11,2)")
	private BigDecimal discount;
	@Column(name = "total_amount", columnDefinition = "decimal(11,2)")
	private BigDecimal totalAmount;
	@Column(name = "order_date")
	private Date orderDate;
	@Column(name = "order_number", length = 32)
	private String orderNumber;
	@Column(name = "approved_step", length = 1)
	private int approvedStep;
	@Column(name = "status", length = 1)
	private int status;
	@Column(name = "staff_id", length = 20)
	private Long staffId;
	
	@OneToMany(mappedBy = "saleOrder")
	private List<SaleOrderDetail> saleOrderDetails;
	
	
}
