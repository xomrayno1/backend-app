package com.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Cache(region = "stockCache",usage = CacheConcurrencyStrategy.READ_WRITE)
public class Stock extends AbstractAuditingEntity implements Serializable{
	
	private static final long serialVersionUID = -4741827952896012635L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_id", length = 20)
	private Long id;
	@Column(name = "name", length = 255)
	private String name;
	@Column(name = "code", length = 32)
	private String code;
	@Column(name = "note", columnDefinition = "TEXT")
	private String note;
	@Column(name = "address", columnDefinition = "TEXT")
	private String address;
	@Column(name = "status", length = 1, nullable = false)
	private int status;
}
