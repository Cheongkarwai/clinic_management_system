package cheong.clinic_management_system.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import cheong.clinic_management_system.entity.Order;
import cheong.clinic_management_system.model.OrderForm;
import cheong.clinic_management_system.service.IAppointmentService;
import cheong.clinic_management_system.service.IOrderService;


@Controller
@RequestMapping("/orders")
public class OrderController {

	private IOrderService orderService;
	private IAppointmentService appointmentService;

	@Autowired
	public OrderController(IOrderService orderService,IAppointmentService appointmentService) {
		this.orderService = orderService;
		this.appointmentService = appointmentService;
	}
	
	@PostMapping("/patient/{patientId}/appointment/{appointmentId}")
	public String processOrder(@PathVariable String patientId, @PathVariable int appointmentId,
			@Valid @ModelAttribute OrderForm orderForm,BindingResult bindingResult,Model model) {
		
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("patientId",patientId);
			model.addAttribute("appointmentId",appointmentId);
			model.addAttribute("orderForm",orderForm);
			model.addAttribute("appointmentDetails",appointmentService.findAppointmentById(appointmentId));
			return "admin/medicine/medicine-checkout";
		}
		
		orderService.saveOrder(orderForm);
		
		return "redirect:/medicines/manage-medicines/index";
	}
	@GetMapping("/index")
	public String viewBillPage(Model model,Authentication authentication) {
		
		String username = authentication.getName();
		model.addAttribute("orders",orderService.findByPatientUsername(username));
		return "order/view-bill";
	}
	@GetMapping("/{id}/checkout")
	public String checkoutPage(@PathVariable String id,Model model) {
		
		model.addAttribute("orderDetailsList",orderService.findOrderDetailsByOrderId(id));
		model.addAttribute("orderTotal",orderService.getOrderTotalByOrderId(id));
		model.addAttribute("orderTax",0);
		model.addAttribute("order",orderService.getOrderPayerByOrderId(id));
		return "order/checkout";
	}
	
	@PutMapping("/{id}")
	public String updateOrder(@PathVariable String id) {
		
		Order order = orderService.findById(id);
		order.setStatus(true);
		orderService.update(order);
		
		return "order/view-bill";
	}
	
	

}
