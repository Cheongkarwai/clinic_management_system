package com.cheong.clinic.patient.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheong.clinic.patient.entity.MedicalHistory;


public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer>{

}
