package com.cheong.clinic.medicine_dispensing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheong.clinic.medicine_dispensing.entity.Medicine;


public interface MedicineRepository extends JpaRepository<Medicine, String>{

}
