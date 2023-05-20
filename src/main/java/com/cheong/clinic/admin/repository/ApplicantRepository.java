package com.cheong.clinic.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cheong.clinic.admin.entity.Applicant;


public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
	

}
