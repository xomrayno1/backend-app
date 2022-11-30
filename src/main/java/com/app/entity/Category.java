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

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Cache(region = "categoryCache",usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = -1297274429094884452L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
	@SequenceGenerator(name = "category_generator", sequenceName = "category_seq", allocationSize = 1)
	@Column(name = "category_id", length = 20)
	private Long id;
	
	@Column(name = "category_name", nullable = false, length = 255)
	private String categoryName;
	
	@Column(name = "category_code", nullable = false, length = 32)
	private String categoryCode;
	
	@Column(name = "description", columnDefinition = "text")
	private String description;

	@Column(name = "object_type", length = 1)
	private Integer objectType;

	@Column(name = "parent_category_id", length = 20)
	private Long parentCategoryId;

	@Column(length = 1, nullable = false)
	private int status;
}
