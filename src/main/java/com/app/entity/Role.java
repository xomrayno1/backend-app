package com.app.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * @author tamnc
 *
 */
@Entity
@Data
@Cache(region = "roleCache",usage = CacheConcurrencyStrategy.READ_ONLY)
public class Role implements Serializable{
	
	private static final long serialVersionUID = 3123918621658118627L;
	
	@Id
	@Column(name = "role_id", length = 20)
	private Long roleId;
	@Column(name = "role_name", length = 255)
	private String roleName;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private Set<Staff> staffs;
}
