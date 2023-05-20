package cheong.clinic_management_system.service;

import java.util.List;

import cheong.clinic_management_system.dto.DoctorDTO;
import cheong.clinic_management_system.entity.Doctor;


public interface IDoctorService {

	List<DoctorDTO> findAllDoctors();
	Doctor findById(String id);
	Doctor loadDoctorById(String id);
	void save(Doctor doctor);
	void update(Doctor doctor);
	void deleteById(String id);
}
