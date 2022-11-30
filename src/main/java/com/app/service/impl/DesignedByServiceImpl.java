package com.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.app.entity.DesignedBy;
import com.app.model.enumtype.Status;
import com.app.repository.DesignedByRepository;
import com.app.service.DesignedByService;

@Service
public class DesignedByServiceImpl extends BaseServiceImpl<DesignedBy> implements DesignedByService{
	
	private final DesignedByRepository designedByRepo;

	public DesignedByServiceImpl(JpaRepository<DesignedBy, Long> jpaRepository, DesignedByRepository designedByRepo) {
		super(jpaRepository);
		this.designedByRepo = designedByRepo;
	}

	@Override
	public Optional<DesignedBy> findByCode(String code) {
		return designedByRepo.findByCode(code);
	}

	@Override
	public void update(DesignedBy instance) {
		designedByRepo.save(instance);
	}

	@Override
	public DesignedBy create(DesignedBy instance) {
		instance.setStatus(Status.ACTIVE.getValue());
		return designedByRepo.save(instance);
	}

	@Override
	public List<DesignedBy> findAll() {
		return designedByRepo.findByStatus(Status.ACTIVE.getValue());
	}

}
