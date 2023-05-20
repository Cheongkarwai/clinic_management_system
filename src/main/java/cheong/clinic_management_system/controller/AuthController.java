package cheong.clinic_management_system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cheong.clinic_management_system.dto.PasswordOTPDto;
import cheong.clinic_management_system.dto.JsonResponse;
import cheong.clinic_management_system.entity.User;
import cheong.clinic_management_system.validator.Group1;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@ModelAttribute
	public User user() {
		return new User();
	}

	@GetMapping("/login/index")
	public String getLoginPage() {

		return "auth/login";
	}

	@GetMapping("/forgot-password/index")
	public String getForgotPasswordPage() {
		return "auth/forgot-password";
	}

	@GetMapping("/sign-up/index")
	public String getSignUpPage() {
		return "sign-up/index";
	}

	@PostMapping("/forgot-password")
	@ResponseBody
	public HttpEntity<?> verifyEmailAddress(@Validated @RequestBody PasswordOTPDto passwordOTPDto,
			BindingResult bindingResult, @RequestParam String action) {

		ResponseEntity<JsonResponse<?>> responseEntity = null;

		if (bindingResult.hasErrors()) {

			List<String> errorMessages = new ArrayList<>();

			for (ObjectError error : bindingResult.getAllErrors()) {
				errorMessages.add(error.getDefaultMessage());
			}

			responseEntity = new ResponseEntity<JsonResponse<?>>(
					new JsonResponse<List<String>>(errorMessages, HttpStatus.UNPROCESSABLE_ENTITY.value()),
					HttpStatus.UNPROCESSABLE_ENTITY);
		} else {

			switch (action) {
			case "send-otp" : 
				// call an api to send sms
				responseEntity = new ResponseEntity<JsonResponse<?>>(
						new JsonResponse<String>("Successfully sent otp to " + passwordOTPDto.getEmailAddress(),
								HttpStatus.OK.value()),
						HttpStatus.OK);
			break;
			case "verify-otp" : 

				// check if otp is the same as the one stored in database
				if (passwordOTPDto.getOtp().equals("123")) {
					responseEntity = new ResponseEntity<JsonResponse<?>>(
							new JsonResponse<String>("OTP matched", HttpStatus.OK.value()), HttpStatus.OK);
				} else {
					responseEntity = new ResponseEntity<JsonResponse<?>>(
							new JsonResponse<String>("OTP does not match", HttpStatus.UNPROCESSABLE_ENTITY.value()),
							HttpStatus.UNPROCESSABLE_ENTITY);
				}
				
			default:
			}
			
		}

		return responseEntity;
	}

}
