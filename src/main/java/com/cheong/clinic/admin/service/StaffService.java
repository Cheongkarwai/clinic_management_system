package com.cheong.clinic.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheong.clinic.admin.entity.Staff;
import com.cheong.clinic.admin.repository.StaffRepository;


@Service
@Transactional
public class StaffService implements IStaffService{

	private StaffRepository staffRepository;
	
	public StaffService(StaffRepository staffRepository) {
		this.staffRepository = staffRepository;
	}
	@Override
	public List<Staff> findAll() {
		return staffRepository.findAll();
	}

	@Override
	public Staff findById(String id) {
		return staffRepository.findById(id).orElseThrow();
	}

	@Override
	public void save(Staff staff) {
		staffRepository.save(staff);
	}

	@Override
	public void update(Staff staff) {
		staffRepository.save(staff);
	}

	@Override
	public void deleteById(String id) {
		staffRepository.deleteById(id);
	}

}
