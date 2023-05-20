package cheong.clinic_management_system.restcontroller;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cheong.clinic_management_system.dto.ManagePatientDTO;
import cheong.clinic_management_system.service.IPatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientRestController {
	
	private IPatientService patientService;
	
	public PatientRestController(IPatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping
	public HttpEntity<?> getAllPatientsByName(@RequestParam String name){
		
		List<ManagePatientDTO> patients = patientService.findByName(name);
		
		return ResponseEntity.ok(patients);
	}
}
