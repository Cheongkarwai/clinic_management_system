package com.cheong.clinic.medicine_dispensing.service;

import java.util.List;

import com.cheong.clinic.medicine_dispensing.entity.Medicine;


public interface IMedicineService {
	
	List<Medicine> findAll();
	Medicine findById(String id);
	void save(Medicine medicine);
	void update(Medicine medicine);
	void deleteById(String id);

}
