package cheong.clinic_management_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cheong.clinic_management_system.entity.Patient;


public interface IPatientRepository extends JpaRepository<Patient, Integer>{
	
	@Query("SELECT p FROM Patient p WHERE p.user.username = :username ")
	Patient findByUsername(@Param("username") String username);
	
	List<Patient> findByNameLike(String name);
}
