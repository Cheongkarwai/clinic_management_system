package com.cheong.clinic.appointment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cheong.clinic.appointment.entity.Appointment;



public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	
}
