package cheong.clinic_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cheong.clinic_management_system.entity.MedicalHistory;


public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer>{

}
