package cheong.clinic_management_system.service;

import java.util.List;

import cheong.clinic_management_system.entity.Staff;


public interface IStaffService {

	List<Staff> findAll();
	Staff findById(String id);
	void save(Staff staff);
	void update(Staff staff);
	void deleteById(String id);
}
