package com.cheong.clinic.patient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cheong.clinic.patient.service.IPatientService;


@Controller
@RequestMapping(path="/user")
public class ProfileController {
	
	private IPatientService patientService;
	
	public ProfileController(IPatientService patientService) {
		this.patientService = patientService;
	}
	
	@GetMapping("/{userId}/profile")
	public String viewProfile(@PathVariable String userId, Model model) {
		
		model.addAttribute("userProfile",patientService.findByUsername(userId));
		return "patient/profile";
	}
}
