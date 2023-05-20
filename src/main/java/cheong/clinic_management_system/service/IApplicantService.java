package cheong.clinic_management_system.service;

import java.util.List;

import cheong.clinic_management_system.dto.ApplicantDto;
import cheong.clinic_management_system.entity.Applicant;


public interface IApplicantService {

	List<Applicant> findAll();
	Applicant findById(int id);
	void save(ApplicantDto applicantDto);
	void update(Applicant applicant);
	void deleteById(int id);
}
