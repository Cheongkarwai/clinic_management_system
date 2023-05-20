package cheong.clinic_management_system.controller;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cheong.clinic_management_system.entity.Medicine;
import cheong.clinic_management_system.entity.OrderDetails;
import cheong.clinic_management_system.model.MedicineAndCheck;
import cheong.clinic_management_system.model.MedicineForm;
import cheong.clinic_management_system.model.OrderForm;
import cheong.clinic_management_system.service.IAppointmentService;
import cheong.clinic_management_system.service.IMedicineService;
import cheong.clinic_management_system.service.IOrderService;



@Controller
@RequestMapping("/medicines")
public class MedicineController {

	private IOrderService orderService;
	private IAppointmentService appointmentService;
	private IMedicineService medicineService;

	public MedicineController(IOrderService orderService,IAppointmentService appointmentService,
			IMedicineService medicineService) {
		this.orderService = orderService;
		this.appointmentService = appointmentService;
		this.medicineService = medicineService;
	}
	
	@GetMapping("/manage-medicines/index")
	public String appointmentsView(Model model)  {
		model.addAttribute("appointmentsThatHaveNoOrder",appointmentService.findAppointmentsNoOrderByStatus(true));
		return "admin/medicine/view-appointment";
	}
	
	@GetMapping("/patient/{patientId}/appointment/{appointmentId}")
	public String medicineSelectionView(Model model,@PathVariable String patientId, @PathVariable String appointmentId) {
	
		List<Medicine> medicines = medicineService.findAll();
		model.addAttribute("medicines",medicines);
		model.addAttribute("patientId", patientId);
		model.addAttribute("appointmentId",appointmentId);
		
		MedicineForm medicineForm = new MedicineForm();
		
		for(int i = 0; i < medicines.size(); i++) {
			medicineForm.getMedicineList().add(new MedicineAndCheck(medicines.get(i).getId(),
					medicines.get(i).getName(),medicines.get(i).getPrice(),
					medicines.get(i).getDescription(),false));
		}
		model.addAttribute("medicineForm",medicineForm);
	
		return "admin/medicine/medicine-selection";
	}
	
	@PostMapping("/patient/{patientId}/appointment/{appointmentId}/checkout")
	public String medicineCheckoutView(@PathVariable int appointmentId, @PathVariable String patientId,
			@ModelAttribute MedicineForm medicineForm,Model model) {

		medicineForm.getMedicineList().removeIf(e->e.isChecked() == false);
		
		OrderForm orderForm = new OrderForm();
		List<OrderDetails> orderDetails = new LinkedList<>();
		orderForm.setOrderDetails(orderDetails);
		
		for(int i = medicineForm.getMedicineList().size() - 1;i >= 0; i--) {
			OrderDetails orderDetail = new OrderDetails();
			orderDetail.setMedicine(new Medicine(
					medicineForm.getMedicineList().get(i).getId(), 
					medicineForm.getMedicineList().get(i).getName(), 
					medicineForm.getMedicineList().get(i).getPrice(), 
					medicineForm.getMedicineList().get(i).getDescription()));
			
			orderDetail.setDescription(medicineForm.getMedicineList().get(i).getDescription());
			orderDetail.setName(medicineForm.getMedicineList().get(i).getName());
			orderDetails.add(orderDetail);
			orderDetail.setSubtotal(medicineForm.getMedicineList().get(i).getPrice());
		}
		
		model.addAttribute("patientId",patientId);
		model.addAttribute("appointmentId",appointmentId);
		model.addAttribute("orderForm", orderForm);
		model.addAttribute("appointmentDetails",appointmentService.findAppointmentById(appointmentId));
		
		return "admin/medicine/medicine-checkout";
	}
}
