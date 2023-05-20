package com.cheong.clinic.admin.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheong.clinic.admin.entity.Applicant;
import com.cheong.clinic.admin.model.ApplicantDto;
import com.cheong.clinic.admin.repository.ApplicantRepository;



@Service
@Transactional
public class ApplicantService implements IApplicantService{

	@PersistenceContext
	private EntityManager em;
	
	//Repositories
	private ApplicantRepository applicantRepository;
	
	public ApplicantService(ApplicantRepository applicantRepository) {
		this.applicantRepository = applicantRepository;
	}
	@Override
	@Transactional(readOnly = true)
	public List<Applicant> findAll(){
		return applicantRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Applicant findById(int id) {
		return applicantRepository.findById(id).orElseThrow();
	}

	@Override
	public void save(ApplicantDto applicantDto){
		Applicant applicant = new Applicant(applicantDto.getName(),
							applicantDto.getSsn(),
							applicantDto.getContact(),
							applicantDto.getEmailAddress());
		
		applicantRepository.save(applicant);;
	}

	@Override
	public void update(Applicant applicant) {
		applicantRepository.save(applicant);
	}

	@Override
	public void deleteById(int id) {
		applicantRepository.deleteById(id);;
	}

}
