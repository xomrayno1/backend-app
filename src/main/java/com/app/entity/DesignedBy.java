package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.app.model.AbstractAuditingEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "designed_by")
public class DesignedBy extends AbstractAuditingEntity {

	private static final long serialVersionUID = -1715662089515330074L;

	@Id
	@SequenceGenerator(name = "designed_by_generator", allocationSize = 1, sequenceName = "designed_by_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "designed_by_generator")
	@Column(name = "designed_by_id", length = 20)
	private Long id;
	
	@Column(length = 255)
	private String name;
	
	@Column(length = 32)
	private String code;
	
	@Column(length = 50)
	private String phone;
	
	@Column(name = "note", columnDefinition = "TEXT")
	private String note;

	@Column(length = 1, nullable = false)
	private int status;
}
