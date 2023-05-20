package cheong.clinic_management_system.service;

import java.util.List;
import java.util.Optional;

import cheong.clinic_management_system.dto.ManagePatientDTO;
import cheong.clinic_management_system.dto.PayerDTO;
import cheong.clinic_management_system.entity.Patient;



public interface IPatientService {
	List<PayerDTO> findAllPatients();
	Patient findById(int id);
	Patient loadPatientById(int id);
	Optional<Patient> findAllAssociationById(int id);
	void save(Patient patient);
	void update(Patient patient);
	void deleteById(int id);
	Patient findByUsername(String username);
	List<ManagePatientDTO> findByName(String name);
}
