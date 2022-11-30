package com.app.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.app.entity.Staff;
import com.app.repository.StaffRepository;
import com.app.request.model.StaffPagingSearchSortModel;
import com.app.service.StaffService;

/**
 * @author tamnc
 *
 */
@Service
public class StaffServiceImpl extends BaseServiceImpl<Staff> implements StaffService{
 
	private final StaffRepository staffRepo;
	 
	public StaffServiceImpl(JpaRepository<Staff, Long> jpaRepository, StaffRepository staffRepo) {
		super(jpaRepository);
		this.staffRepo = staffRepo;
	}

	@Override
	public Page<Staff> findAll(StaffPagingSearchSortModel cpssm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Staff> findByStaffCode(String staffCode) {
		return staffRepo.findByStaffCode(staffCode);
	}

	@Override
	public Optional<Staff> findByEmail(String email) {
		return staffRepo.findByEmail(email);
	}

//	@Override
//	public Optional<Staff> findByStaffId(Long staffId) {
//		return staffRepo.findById(staffId);
//	}

	@Override
	public void update(Staff instance) {
		staffRepo.save(instance);
	}

	@Override
	public Staff create(Staff instance) {
		return staffRepo.save(instance);
	}

//	@Override
//	public void delete(Staff staff) {
//		staff.setStatus(Status.IN_ACTIVE.getValue());
//		staffRepo.save(staff);
//	}
//
//	@Override
//	public void deleteAll(List<Staff> staffs) {
//		List<Staff> listDelete = staffs.stream().map(item -> {
//			item.setStatus(Status.IN_ACTIVE.getValue());
//			return item;
//		}).collect(Collectors.toList());
//		staffRepo.saveAll(listDelete);
//	}
//
//	@Override
//	public List<Staff> findAllByStaffIds(List<Long> staffIds) {
//		return staffRepo.findAllById(staffIds);
//	}

}
