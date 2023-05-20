package com.cheong.clinic.admin.service;

import java.util.List;

import com.cheong.clinic.admin.entity.Staff;


public interface IStaffService {

	List<Staff> findAll();
	Staff findById(String id);
	void save(Staff staff);
	void update(Staff staff);
	void deleteById(String id);
}
