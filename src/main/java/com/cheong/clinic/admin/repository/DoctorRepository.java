package com.cheong.clinic.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheong.clinic.admin.entity.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor, String> {


}
