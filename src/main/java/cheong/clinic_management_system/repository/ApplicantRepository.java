package cheong.clinic_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cheong.clinic_management_system.entity.Applicant;


public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
	

}
