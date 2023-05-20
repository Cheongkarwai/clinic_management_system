package cheong.clinic_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import cheong.clinic_management_system.service.IApplicantService;
import cheong.clinic_management_system.service.IAppointmentService;
import cheong.clinic_management_system.service.IDoctorService;
import cheong.clinic_management_system.service.IMedicineService;
import cheong.clinic_management_system.service.IPatientService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private IPatientService patientService;
	private IApplicantService applicantService;
	private IDoctorService doctorService;
	private IAppointmentService appointmentService;
	private IMedicineService medicineService;
	
	public AdminController(IPatientService patientService,IApplicantService applicantService,
			IDoctorService doctorService,IAppointmentService appointmentService,IMedicineService medicineService) {
		this.patientService = patientService;
		this.applicantService = applicantService;
		this.doctorService = doctorService;
		this.appointmentService = appointmentService;
		this.medicineService = medicineService;
	}
	
	
	
//	@ModelAttribute
//	public MedicineForm medicineForm() {
//		MedicineForm medicineForm = new MedicineForm();
//		//medicineForm.setMedicineList(medicineService.findAll());
//		return medicineForm;
//	}
//	
//	@ModelAttribute("appointment")
//	public AppointmentForm appointmentForm() {
//		return new AppointmentForm();
//	}
//	
	@GetMapping
	public String getAdminPage() {
		return "admin/index";
	}
	
	@GetMapping("/dashboard")
	public String getDashboardPage() {
		
		System.out.println("Hi");
		
		return "admin/index";
	}
	
	@GetMapping("/manage-patients")
	public String getManagePatientsPage() {
		return "/admin/manage-patient";
	}
	
	@GetMapping("/manage-appointments")
	public String getManageAppointmentPage() {
		return "admin/appointment/manage-appointment";
	}

	
//	@GetMapping("/patients")
//	public String getManagePatientPage(Model model) {
//		
//		model.addAttribute("patient", new PatientForm());
//		
//		model.addAttribute("patientList", patientService.findAll());
//		model.addAttribute("applicantList", applicantService.findAll());
//		
//		return "admin/manage-patient";
//	}
	
//	@GetMapping("/appointments")
//	public String getManageAppointmentPage(Model model) {
//		try {
//			Optional<Appointment> appointment = appointmentService.findLatestAppointment();
//			model.addAttribute("doctorList", doctorService.findAll());
//			model.addAttribute("patientList", patientService.findAll());
//			model.addAttribute("appointmentList",appointmentService.findAppointmentsByStat(false));
//			
//			if(appointment.isEmpty()) {
//				LocalDateTime today = LocalDateTime.now();
//				//Split the date using delimiter T
//				String [] date = today.toString().split("T");
//				model.addAttribute("latestAppointment",date[0]+"T"+(today.getHour()+1)+":"+today.getMinute());
//			}
//			else {
//				model.addAttribute("latestAppointment",appointment.get().getAppointmentEndDateTime());
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return "admin/manage-appointment";
//	}
//	
//	@GetMapping("/medicines")
//	public String getMedicationPage(Model model)  {
//	
//		model.addAttribute("appointmentsThatHaveNoOrder",appointmentService.findAppointmentsThatHaveNoOrderByStat(true));
//		return "admin/medicine-dispensing";
//	}
//	
//	@GetMapping("/dispense-medicine/patient/{patientId}/appointment/{appointmentId}")
//	public String getDispenseMedicinePage(Model model,@PathVariable String patientId, @PathVariable String appointmentId) {
//	
//		model.addAttribute("medicines",medicineService.findAll());
//		model.addAttribute("patientId", patientId);
//		model.addAttribute("appointmentId",appointmentId);
//	
//		return "admin/dispense-medicine";
//	}
}
