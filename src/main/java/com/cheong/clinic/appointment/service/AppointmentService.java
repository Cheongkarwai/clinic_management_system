package com.cheong.clinic.appointment.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheong.clinic.admin.repository.DoctorRepository;
import com.cheong.clinic.appointment.entity.Appointment;
import com.cheong.clinic.appointment.model.AppointmentForm;
import com.cheong.clinic.appointment.repository.AppointmentRepository;
import com.cheong.clinic.patient.repository.PatientRepository;



@Service
@Transactional
public class AppointmentService implements IAppointmentService {

	private AppointmentRepository appointmentRepository;
	private PatientRepository patientRepository;
	private DoctorRepository doctorRepository;

	private Comparator<Appointment> sortAppointmentInDesc = (a, b) -> {
		if (a.getAppointmentStartDateTime().compareTo(b.getAppointmentStartDateTime()) > 0) {
			return -1;
		}
		if (a.getAppointmentStartDateTime().compareTo(b.getAppointmentStartDateTime()) == 0) {
			return 0;
		}
		return 1;
	};

	private AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository,
			DoctorRepository doctorRepository) {
		this.appointmentRepository = appointmentRepository;
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Appointment> findAll() {
		return appointmentRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Appointment findAppointmentById(int id) {

		Optional<Appointment> optional = appointmentRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new NullPointerException("Appointment not found");

	}

	@Override
	public void save(AppointmentForm appointmentForm) {
		
		Appointment appointment = new Appointment();
		appointment.setAppointmentStartDateTime(appointmentForm.getStartDateTime());
		appointment.setAppointmentEndDateTime(appointmentForm.getEndDateTime());
		appointment.setDateTimeCreated(LocalDateTime.now());
		appointment.setDoctor(doctorRepository.findById(appointmentForm.getDoctorId()).orElseThrow());
		appointment.setPatient(patientRepository.findById(Integer.parseInt(appointmentForm.getPatientId())).orElseThrow());
		appointmentRepository.save(appointment);
	}

	@Override
	public void update(Appointment appointment) {
		appointmentRepository.save(appointment);
	}

	@Override
	public void deleteById(int id) {
		appointmentRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Appointment> findAppointmentsByDateTime(LocalDateTime localDateTime) {

		return appointmentRepository.findAll().stream().filter(e -> {
			if (e.getAppointmentStartDateTime().compareTo(localDateTime) > 0) {
				return true;
			}
			return false;
		}).collect(Collectors.toList());
	}

	@Override
	public Optional<Appointment> findLatestAppointment() {

		return appointmentRepository.findAll().stream().sorted(sortAppointmentInDesc).findFirst();
	}
	/*
	 * @Override public List<Appointment> findTodayAppointments(){
	 * 
	 * return appointmentRepository.findAll() .stream()
	 * .filter(e->e.getAppointmentStartDateTime().toLocalDate().compareTo(LocalDate.
	 * now()) == 0) .sorted(sortAppointmentInAsc) .toList(); }
	 */

	@Override
	@Transactional(readOnly = true)
	public List<Appointment> findAppointmentsNoOrderByStatus(boolean stat) {

		return appointmentRepository.findAll()
				.stream()
				.filter(e -> e.getOrder() == null && e.getStatus() == stat)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Appointment> findAppointmentsByStatus(boolean status) {

		appointmentRepository.findAll().stream().filter(e -> e.getStatus() == status)
		.forEach(item->System.out.println(item.getPatient().getId()));
		
		return appointmentRepository.findAll().stream().filter(e -> e.getStatus() == status)
				.sorted(sortAppointmentInDesc).collect(Collectors.toList());
	}

	/*
	 * @Override public void save(Appointment appointment,int patientId, String
	 * doctorId) { Patient patientProxy = patientRepository.loadById(patientId);
	 * Doctor doctorProxy = doctorRepository.loadById(doctorId);
	 * appointment.setPatient(patientProxy); appointment.setDoctor(doctorProxy);
	 * appointmentRepository.save(appointment);
	 * 
	 * }
	 */

	/*
	 * @Override public Appointment findAllAssociationById(int id) {
	 * 
	 * Appointment appointment = appointmentRepository.findById(id).get();
	 * appointment.getDoctor(); appointment.getPatient();
	 * 
	 * return appointment; }
	 */
	@Override
	public void updateAppointmentStatusById(boolean status, int id) {
		Appointment appointment = appointmentRepository.findById(id).get();
		appointment.setStatus(status);
		appointmentRepository.save(appointment);
	}


}
