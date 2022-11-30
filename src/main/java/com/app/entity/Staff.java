package com.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Cache(region = "staffCache",usage = CacheConcurrencyStrategy.READ_WRITE)
public class Staff extends AbstractAuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 3189424706917169225L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_generator")
	@SequenceGenerator(name = "staff_generator", sequenceName = "staff_seq", allocationSize = 1)
	@Column(name = "staff_id", length = 20)
	private Long id;
	@Column(name = "staff_code", length = 32)
	private String staffCode;
	@Column(name = "staff_name", length = 255)
	private String staffName;
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
 
	//auth
	@Column(name = "user_name")
	private String userName;
	@Column(name = "hash_password")
	private String hashPassword;
	
	@ManyToMany
	@JoinTable(name = "staff_role"
			, joinColumns = @JoinColumn(name = "staff_id")
			, inverseJoinColumns = @JoinColumn(name = "role_id")
		)
	private Set<Role> roles;

}
