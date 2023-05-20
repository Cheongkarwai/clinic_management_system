package com.cheong.clinic.admin.service;

import java.util.List;

import com.cheong.clinic.admin.entity.Applicant;
import com.cheong.clinic.admin.model.ApplicantDto;


public interface IApplicantService {

	List<Applicant> findAll();
	Applicant findById(int id);
	void save(ApplicantDto applicantDto);
	void update(Applicant applicant);
	void deleteById(int id);
}
