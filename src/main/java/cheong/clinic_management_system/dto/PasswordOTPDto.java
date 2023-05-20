package cheong.clinic_management_system.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PasswordOTPDto {

	@NotEmpty
	@Email
	private String emailAddress;
	
	private String otp;
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getOtp() {
		return otp;
	}
	
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
}
