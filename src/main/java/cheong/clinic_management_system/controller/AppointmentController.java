package cheong.clinic_management_system.controller;
import java.beans.PropertyEditorSupport;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import cheong.clinic_management_system.dto.ApplicantDto;
import cheong.clinic_management_system.entity.Applicant;
import cheong.clinic_management_system.entity.Appointment;
import cheong.clinic_management_system.entity.Patient;
import cheong.clinic_management_system.model.ApplicantForm;
import cheong.clinic_management_system.model.AppointmentForm;
import cheong.clinic_management_system.service.IApplicantService;
import cheong.clinic_management_system.service.IAppointmentService;
import cheong.clinic_management_system.service.IDoctorService;
import cheong.clinic_management_system.service.IPatientService;
import cheong.clinic_management_system.validator.Group1;
import cheong.clinic_management_system.validator.Group2;
import net.bytebuddy.implementation.bind.MethodDelegationBinder.BindingResolver;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

	private IAppointmentService appointmentService;
	private IApplicantService applicantService;
	private IPatientService patientService;
	private IDoctorService doctorService;

	@Autowired
	public AppointmentController(IAppointmentService appointmentService, IApplicantService applicantService,
			IPatientService patientService, IDoctorService doctorService) {
		this.appointmentService = appointmentService;
		this.applicantService = applicantService;
		this.patientService = patientService;
		this.doctorService = doctorService;
	}

	@GetMapping("/manage-appointment/index")
	public String index(Model model) {

		Optional<Appointment> appointment = appointmentService.findLatestAppointment();
		model.addAttribute("doctorList", doctorService.findAllDoctors());
		model.addAttribute("patientList", patientService.findAllPatients());
		model.addAttribute("appointmentList", appointmentService.findAppointmentsByStatus(false));
		if (appointment.isEmpty()) {
			LocalDateTime today = LocalDateTime.now();
			String[] dateArr = today.toString().split("T");
			model.addAttribute("latestAppointment", dateArr[0] + "T" + (today.getHour() + 1) + ":" + today.getMinute());
		} else {
			model.addAttribute("latestAppointment", appointment.get().getAppointmentEndDateTime());
		}

		return "admin/appointment/manage-appointment";
	}

	
	@GetMapping("/add-appointment/index")
	public String getAddAppointmentPage(Model model) {

		model.addAttribute("patients", patientService.findAllPatients());
		model.addAttribute("doctorList", doctorService.findAllDoctors());
		model.addAttribute("appointment", new AppointmentForm());
		Optional<Appointment> appointment = appointmentService.findLatestAppointment();
		if (appointment.isEmpty()) {
			LocalDateTime today = LocalDateTime.now();
			String[] dateArr = today.toString().split("T");
			model.addAttribute("latestAppointment", dateArr[0] + "T" + (today.getHour() + 1) + ":" + today.getMinute());
		} else {
			model.addAttribute("latestAppointment", appointment.get().getAppointmentEndDateTime());
		}
		return "admin/appointment/add-appointment";
	}

	@GetMapping("/{appointmentId}/patients/{patientId}/details")
	public String showPatientDetails(Model model, @PathVariable String patientId, @PathVariable String appointmentId) {
		Patient patient = patientService.findById(Integer.parseInt(patientId));
		model.addAttribute("patient", patient);
		return "admin/appointment/view-patient-details";
	}
	
	@PutMapping("/{appointmentId}/status/{stat}")
	public String updateAppointmentStatus(@PathVariable int appointmentId, @PathVariable(name="stat") boolean status) {

		appointmentService.updateAppointmentStatusById(status,appointmentId);

		return "redirect:/appointments/manage-appointment/index";
	}

	@DeleteMapping("/{appointmentId}")
	public String deleteAppointment(@PathVariable int appointmentId) {
		
		appointmentService.deleteById(appointmentId);

		return "redirect:/appointments/manage-appointment/index";
	}

	// add appointment
	@PostMapping("/add-appointment/add")
	public String add(@Valid @ModelAttribute("appointment") AppointmentForm appointmentForm,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {

			model.addAttribute("patients", patientService.findAllPatients());
			model.addAttribute("doctorList", doctorService.findAllDoctors());

			Optional<Appointment> appointment = appointmentService.findLatestAppointment();
			if (appointment.isEmpty()) {
				LocalDateTime today = LocalDateTime.now();
				// Split the date using delimiter T
				String[] date = today.toString().split("T");
				model.addAttribute("latestAppointment",
						date[0] + "T" + (today.getHour() + 1) + ":" + today.getMinute());
			} else {
				model.addAttribute("latestAppointment", appointment.get().getAppointmentEndDateTime());
			}

			return "admin/appointment/add-appointment";
		}

		appointmentService.save(appointmentForm);


		return "redirect:/appointments/manage-appointment/index";
	}

	@PostMapping("/book/revalidate")
	public String revalidate(
			@Validated(value = { Group2.class }) @ModelAttribute("applicant") ApplicantDto applicantDto,
			BindingResult bindingResult) {
		
		if (bindingResult.hasFieldErrors()) {
			return "index";
		}

		applicantService.save(applicantDto);

		return "redirect:/";
	}

	@PostMapping("/book")
	public String bookAppointment(
			@Validated(value = { Group1.class }) @ModelAttribute("applicant") ApplicantDto applicantDto,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasFieldErrors()) {
			return "index";
		}
		return "forward:/appointments/book/revalidate";
	}
}
