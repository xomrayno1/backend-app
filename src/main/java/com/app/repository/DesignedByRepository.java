package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.DesignedBy;

@Repository
public interface DesignedByRepository extends JpaRepository<DesignedBy, Long>{

	Optional<DesignedBy> findByCode(String code);
	
	List<DesignedBy> findByStatus(int status);
	
}
