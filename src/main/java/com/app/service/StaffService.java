package com.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.app.entity.Staff;
import com.app.request.model.StaffPagingSearchSortModel;

/**
 * @author tamnc
 *
 */
public interface StaffService  extends BaseService<Staff>{

	Page<Staff> findAll(StaffPagingSearchSortModel cpssm);

	Optional<Staff> findByStaffCode(String staffCode);

	Optional<Staff> findByEmail(String email);

//	Optional<Staff> findByStaffId(Long staffId);

	void update(Staff instance);

	Staff create(Staff instance);

//	void delete(Staff staff);
//
//	void deleteAll(List<Staff> staffs);
//
//	List<Staff> findAllByStaffIds(List<Long> staffIds);

}
