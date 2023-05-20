package com.cheong.clinic.patient.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheong.clinic.order.entity.Order;
import com.cheong.clinic.patient.entity.Patient;
import com.cheong.clinic.patient.model.ManagePatientDTO;
import com.cheong.clinic.patient.repository.PatientRepository;
import com.cheong.clinic.payment.model.PayerDTO;


@Service
@Transactional
public class PatientService implements IPatientService {

	private PatientRepository patientRepository;

	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PayerDTO> findAllPatients() {
		List<Patient> patients = patientRepository.findAll();
		
		return patients.stream()
					.map(n->new PayerDTO(
							n.getId(),
							n.getName(),
							n.getDateOfBirth(),
							n.getSSN(),
							n.getContact(),
							n.getEmailAddress(),
							n.getAddress(),
							n.getGender()))
					.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Patient findById(int id) {
		
		Optional<Patient> optional = patientRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Patient loadPatientById(int id) {
		
		return patientRepository.findById(id).orElseThrow();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Patient> findAllAssociationById(int id) {
		Patient patient = patientRepository.findById(id).get();
		patient.getUser();
		patient.getOrderList().forEach(e->System.out.println(e.getId()));;
		for(Iterator<Order> itr = patient.getOrderList().iterator(); itr.hasNext();) {
			Order order = itr.next();
			order.getOrderDetailsList().forEach(e->System.out.println(e.getId()));;
		}
		patient.getAppointmentList().forEach(e->System.out.println(e.getId()));;
		
		
		return Optional.ofNullable(patient);
	}
	@Override
	public void save(Patient patient) {
		
		try {
			patientRepository.save(patient);
		}
		catch(ConstraintViolationException exception) {
			throw exception;
		}
	}

	@Override
	public void update(Patient patient) {
		patientRepository.save(patient);
	}

	@Override
	public void deleteById(int id) {
		patientRepository.deleteById(id);
	}

	@Override
	public Patient findByUsername(String username) {
		
		return patientRepository.findByUsername(username);
	}

	@Override
	public List<ManagePatientDTO> findByName(String name) {
		
		return patientRepository.findByNameLike("%"+name+"%")
					.stream()
					.map(patient->{
						return new ManagePatientDTO(
								patient.getId(),
								patient.getName(),
								patient.getSSN(),
								patient.getContact());
					})
					.collect(Collectors.toList());
	}



	
}
