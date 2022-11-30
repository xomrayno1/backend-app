package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entity.DesignedBy;

public interface DesignedByService extends BaseService<DesignedBy>{
 
	Optional<DesignedBy> findByCode(String code);
 
	void update(DesignedBy instance);

	DesignedBy create(DesignedBy instance);
	
	List<DesignedBy> findAll();
}
