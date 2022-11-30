package com.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Cache(region = "customerCache",usage = CacheConcurrencyStrategy.READ_WRITE)
public class Customer extends AbstractAuditingEntity implements Serializable{

	private static final long serialVersionUID = -3128849429118737279L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
	@SequenceGenerator(name = "customer_generator", sequenceName = "customer_seq", allocationSize = 1)
	@Column(name = "customer_id", length = 20)
	private Long id;
	@Column(name = "customer_code", length = 32)
	private String customerCode;
	@Column(name = "customer_name", length = 255)
	private String customerName;
	@Column(name = "email", length = 50)
	private String email;
	@Column(name = "phone", length = 12)
	private String phone;
	@Column(name = "address", length = 255)
	private String address;
	@Column(name = "birth_day")
	@Temporal(TemporalType.DATE)
	private Date birthDay;
	@Column(name = "gender", length = 1, nullable = false)
	private int gender;
	@Column(name = "status", length = 1, nullable = false)
	private int status;
	
}
