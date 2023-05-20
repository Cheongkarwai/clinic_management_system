package cheong.clinic_management_system.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import cheong.clinic_management_system.validator.Group1;
import cheong.clinic_management_system.validator.Group2;
import cheong.clinic_management_system.validator.SSN;

public class ApplicantDto {

	@NotEmpty(message="Name is required", groups = Group1.class)
	private String name;
	
	@NotEmpty(message="SSN is required",groups = Group1.class)
	@SSN(message="Invalid SSN",groups = Group2.class)
	private String ssn;
	
	@NotEmpty(message="Contact number is required",groups = Group1.class)
	private String contact;
	
	@NotEmpty(message="Email address is required",groups = Group1.class)
	@Email(message="Invalid email address",groups = Group2.class)
	private String emailAddress;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	
}
