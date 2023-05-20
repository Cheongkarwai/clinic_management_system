package cheong.clinic_management_system.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import cheong.clinic_management_system.entity.Appointment;
import cheong.clinic_management_system.model.AppointmentForm;


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
