package com.cheong.clinic.patient.service;

import java.util.List;
import java.util.Optional;

import com.cheong.clinic.patient.entity.Patient;
import com.cheong.clinic.patient.model.ManagePatientDTO;
import com.cheong.clinic.payment.model.PayerDTO;



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
