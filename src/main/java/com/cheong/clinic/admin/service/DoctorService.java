package com.cheong.clinic.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheong.clinic.admin.entity.Doctor;
import com.cheong.clinic.admin.model.DoctorDTO;
import com.cheong.clinic.admin.repository.DoctorRepository;


@Service
@Transactional
public class DoctorService implements IDoctorService{
	
	private DoctorRepository doctorRepository;

	private DoctorService(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}
	@Override
	@Transactional(readOnly = true)
	public List<DoctorDTO> findAllDoctors() {
		
		return doctorRepository.findAll()
				.stream()
				.map(n->new DoctorDTO(n.getId(),
						n.getName(),
						n.getDateOfBirth(),
						n.getSSN(),
						n.getContact(),
						n.getEmailAddress(),
						n.getAddress(),
						n.getGender(),
						n.getSpecialization()))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Doctor findById(String id) {
		Doctor doctor = doctorRepository.findById(id).orElseThrow();
		Hibernate.initialize(doctor.getAppointmentList());
		return doctor;
	}
	
	@Override
	public Doctor loadDoctorById(String id) {
		return doctorRepository.findById(id).orElseThrow();
	}

	@Override
	public void save(Doctor doctor) {
		doctorRepository.save(doctor);
	}

	@Override
	public void update(Doctor doctor) {
		doctorRepository.save(doctor);
	}

	@Override
	public void deleteById(String id) {
		doctorRepository.deleteById(id);
	}

}
