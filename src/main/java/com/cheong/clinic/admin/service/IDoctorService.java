package com.cheong.clinic.admin.service;

import java.util.List;

import com.cheong.clinic.admin.entity.Doctor;
import com.cheong.clinic.admin.model.DoctorDTO;


public interface IDoctorService {

	List<DoctorDTO> findAllDoctors();
	Doctor findById(String id);
	Doctor loadDoctorById(String id);
	void save(Doctor doctor);
	void update(Doctor doctor);
	void deleteById(String id);
}
