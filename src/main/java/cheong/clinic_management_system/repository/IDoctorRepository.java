package cheong.clinic_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cheong.clinic_management_system.entity.Doctor;


public interface IDoctorRepository extends JpaRepository<Doctor, String> {


}
