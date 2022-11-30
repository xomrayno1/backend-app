package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.app.entity.Staff;

/**
 * @author tamnc
 *
 */
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>, JpaSpecificationExecutor<Staff>{
	Staff findByUserName(String userName);
	
	Optional<Staff> findByStaffCode(String staffCode);
	
	Optional<Staff> findByEmail(String email);
}
