package cheong.clinic_management_system.controller;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cheong.clinic_management_system.dto.ApplicantDto;
import cheong.clinic_management_system.model.ApplicantForm;


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
