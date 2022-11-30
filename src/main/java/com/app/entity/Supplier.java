package com.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Cache(region = "supplierCache",usage = CacheConcurrencyStrategy.READ_WRITE)
public class Supplier extends AbstractAuditingEntity implements Serializable{
	private static final long serialVersionUID = 8566985986403997926L;
	
	@Id
	@SequenceGenerator(name = "supplier_generator", allocationSize = 1, sequenceName = "supplier_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_generator")
	@Column(name = "supplier_id", length = 20)
	private Long id;
	@Column(length = 255)
	private String name;
	@Column(length = 32)
	private String code;
	@Column(length = 50)
	private String email;
	@Column(length = 12)
	private String phone;
	@Column(name = "note", columnDefinition = "TEXT")
	private String note;
	@Column(name = "tax_code", length = 100)
	private String taxCode;
	@Column(length = 1, nullable = false)
	private int status;

}
