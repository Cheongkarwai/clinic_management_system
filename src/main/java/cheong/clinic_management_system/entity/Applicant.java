package cheong.clinic_management_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="Applicant",schema="spring_mvc_clinic")
public class Applicant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name",nullable = false)
	private String name;
	
	@Column(name="SSN",nullable = false)
	@NaturalId
	private String SSN;
	
	@Column(name="contact",nullable = false)
	private String contact;
	
	@Column(name="emailAddress",nullable = false)
	private String emailAddress;
	
	public Applicant() {
		
	}
	
	public Applicant(String name, String SSN, String contact, String emailAddress) {
		this.name = name;
		this.SSN = SSN;
		this.contact = contact;
		this.emailAddress = emailAddress;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
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
