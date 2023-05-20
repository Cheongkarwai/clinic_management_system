package com.cheong.clinic.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cheong.clinic.order.service.IOrderService;


@Controller
@RequestMapping("/bills")
public class BillController {
	
	private IOrderService orderService;
	
	@Autowired
	public BillController(IOrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/index")
	public String getBillPage(Model model,Authentication authentication) {
		
		String username = authentication.getName();
		model.addAttribute("orders",orderService.findByPatientUsername(username));
		return "order/view-bill";
	}
}
