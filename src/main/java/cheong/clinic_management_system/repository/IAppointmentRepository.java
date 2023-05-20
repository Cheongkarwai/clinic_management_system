package cheong.clinic_management_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cheong.clinic_management_system.entity.Appointment;



public interface IAppointmentRepository extends JpaRepository<Appointment, Integer>{

	
}
