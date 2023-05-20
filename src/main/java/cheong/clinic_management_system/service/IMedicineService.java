package cheong.clinic_management_system.service;

import java.util.List;

import cheong.clinic_management_system.entity.Medicine;


public interface IMedicineService {
	
	List<Medicine> findAll();
	Medicine findById(String id);
	void save(Medicine medicine);
	void update(Medicine medicine);
	void deleteById(String id);

}
