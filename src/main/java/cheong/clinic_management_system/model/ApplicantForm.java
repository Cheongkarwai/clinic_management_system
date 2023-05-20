package cheong.clinic_management_system.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import cheong.clinic_management_system.validator.Contact;
import cheong.clinic_management_system.validator.Group1;
import cheong.clinic_management_system.validator.Group2;
import cheong.clinic_management_system.validator.Letter;


public class ApplicantForm {

	@NotEmpty(message="Name is required",groups= {Group1.class})
	@Letter(message="Invalid name",groups= {Group2.class})
	private String name;
	
	@NotEmpty(message="SSN is required",groups= {Group1.class})
	@cheong.clinic_management_system.validator.SSN(message="Invalid SSN",groups= {Group2.class})
	private String SSN;
	
	@NotEmpty(message="Contact is required",groups= {Group1.class})
	@Contact(message="Invalid contact number",groups= {Group2.class})
	private String contact;
	
	@NotEmpty(message="Email is required",groups= {Group1.class})
	@Email(message="Invalid email address",groups= {Group2.class})
	private String emailAddress;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setSSN(String SSN) {
		this.SSN = SSN;
	}
	public String getSSN() {
		return SSN;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContact() {
		return contact;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
}
