package com.cheong.clinic.main.controller;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cheong.clinic.admin.model.ApplicantDto;
import com.cheong.clinic.admin.model.ApplicantForm;


@Controller
@RequestMapping("/")
public class HomeController {
	
	private Logger logger = Logger.getLogger(HomeController.class.getName());

	@ModelAttribute("applicant")
	public ApplicantDto applicant() {
		return new ApplicantDto();
	}
	
	@GetMapping
	public String getHomePage() {
		return "index";
	}
}
