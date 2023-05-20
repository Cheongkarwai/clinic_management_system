package com.cheong.clinic.appointment.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.cheong.clinic.appointment.entity.Appointment;
import com.cheong.clinic.appointment.model.AppointmentForm;


public interface IAppointmentService {
	
	List<Appointment> findAll();
	List<Appointment> findAppointmentsByStatus(boolean status);
	Appointment findAppointmentById(int id);
	List<Appointment> findAppointmentsByDateTime(LocalDateTime localDateTime);
	List<Appointment> findAppointmentsNoOrderByStatus(boolean status);
	void update(Appointment appointment);
	void updateAppointmentStatusById(boolean status,int id);
	void deleteById(int id);
	Optional<Appointment> findLatestAppointment();
	void save(AppointmentForm appointment);
}
