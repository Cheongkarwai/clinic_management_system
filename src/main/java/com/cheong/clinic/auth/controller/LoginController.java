package com.cheong.clinic.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cheong.clinic.auth.entity.User;


@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@ModelAttribute
	public User user() {
		return new User();
	}
	

}
