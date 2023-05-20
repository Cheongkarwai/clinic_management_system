package cheong.clinic_management_system.controller;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import cheong.clinic_management_system.entity.Address;
import cheong.clinic_management_system.entity.Patient;
import cheong.clinic_management_system.entity.User;
import cheong.clinic_management_system.model.PatientForm;
import cheong.clinic_management_system.service.IApplicantService;
import cheong.clinic_management_system.service.IPatientService;


@Controller
@RequestMapping("/patients")
public class PatientController {

	private IPatientService patientService;
	private IApplicantService applicantService;
	private UserDetailsManager userDetailsManager;
	private PasswordEncoder passwordEncoder;

	private Logger logger = Logger.getLogger(PatientController.class.getName());

	public PatientController(IPatientService patientService, IApplicantService applicantService,
			UserDetailsManager userDetailsManager,PasswordEncoder passwordEncoder) {
		this.patientService = patientService;
		this.applicantService = applicantService;
		this.userDetailsManager = userDetailsManager;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/manage-patient/index")
	public String index(Model model) {
		model.addAttribute("patientList", patientService.findAllPatients());
		return "/admin/patient/manage-patient";
	}

	@GetMapping("/{patientId}/edit")
	public String editView(@PathVariable String patientId, Model model) {

		Patient patient = null;

		patient = patientService.findById(Integer.parseInt(patientId));

		PatientForm patientForm = new PatientForm(patient.getId(), patient.getName(), patient.getDateOfBirth(),
				patient.getSSN(), patient.getGender(), patient.getContact(), patient.getEmailAddress(),
				patient.getAddress().getAddress_line_1(), patient.getAddress().getAddress_line_2(),
				patient.getAddress().getPostcode(), patient.getAddress().getState(), patient.getAddress().getCity());

		model.addAttribute("patientForm", patientForm);

		return "/admin/patient/edit-patient";
	}

	@GetMapping("/add-patient/index")
	public String addView(Model model) {
		model.addAttribute("patient", new PatientForm());
		model.addAttribute("patientList", patientService.findAllPatients());
		model.addAttribute("applicantList", applicantService.findAll());

		return "/admin/patient/add-patient";
	}

	@PostMapping(value={"/{applicantId}/add","/add"})
	public String add(@PathVariable(required = false) String applicantId,
			@Valid @ModelAttribute("patient") PatientForm patientForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "patient/manage-patient";
		}

		if (applicantId != null) {
			applicantService.deleteById(Integer.parseInt(applicantId));
		}

		Patient patient = new Patient(patientForm.getName(), patientForm.getDateOfBirth(), 
				patientForm.getSSN(), patientForm.getContact(), patientForm.getEmailAddress(),
				new Address(patientForm.getAddress_line_1(), patientForm.getAddress_line_2(), 
							patientForm.getPostcode(),patientForm.getCity(), patientForm.getState()),
				patientForm.getGender());
		
		User user = new User(patientForm.getSSN(),
				passwordEncoder.encode(patientForm.getSSN()),
				"ROLE_USER",
				true);
		
		userDetailsManager.createUser(user);
		patient.setUser(user);
		patientService.save(patient);

		return "redirect:/patients/manage-patient/index";
	}

	@PostMapping("/{patientId}/delete")
	public String delete(@PathVariable String patientId) {

		Patient patient = patientService.findById(Integer.parseInt(patientId));
		userDetailsManager.deleteUser(patient.getUser().getUsername());
		return "redirect:/patients/manage-patient/index";
	}

	@PostMapping("/{patientId}/update")
	public String update(@Valid @ModelAttribute("patientForm") PatientForm patientForm, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("patientForm", patientForm);
			return "admin/patient/edit-patient";
		}

		Patient patient = patientService.findById(patientForm.getId());
		patient.setName(patientForm.getName());
		patient.getAddress().setAddress_line_1(patientForm.getAddress_line_1());
		patient.getAddress().setAddress_line_2(patientForm.getAddress_line_2());
		patient.getAddress().setState(patientForm.getState());
		patient.getAddress().setCity(patientForm.getCity());
		patient.getAddress().setPostcode(patientForm.getPostcode());
		patient.setDateOfBirth(patientForm.getDateOfBirth());
		patient.setEmailAddress(patientForm.getEmailAddress());
		patient.setGender(patientForm.getGender());
		patient.setSSN(patientForm.getSSN());
		patient.setContact(patientForm.getContact());

		patientService.update(patient);

		return "redirect:/patients/manage-patient/index";
	}

}
