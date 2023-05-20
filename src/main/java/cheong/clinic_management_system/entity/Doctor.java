package cheong.clinic_management_system.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="doctor")
public class Doctor{

	@Id
	@GeneratedValue(generator="doctor_id_generator")
	@GenericGenerator(name="doctor_id_generator",
	strategy="cheong.clinic_management_system.entity.DoctorIdGenerator")
	@Column(name="id")
	private String id;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="dateOfBirth",nullable=false)
	private LocalDate dateOfBirth;
	
	@Column(name="SSN",nullable=false)
	private String SSN;
	
	@Column(name="contact",nullable=false)
	private String contact;
	
	@Column(name="emailAddress",nullable=false)
	private String emailAddress;
	
	@Embedded
	private Address address;
	
	@Column(name="gender",nullable=false)
	private char gender;
	
	@Column(name="specialization",nullable=false)
	private String specialization;
	
	@OneToMany(mappedBy = "doctor",cascade=CascadeType.ALL)
	@Column(name="appointmentId")
	private List<Appointment> appointmentList;

	public Doctor() {

	}

	public Doctor(String name, LocalDate dateOfBirth, String SSN, char gender, String contact,
			String emailAddress, String specialization,Address address) {
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.SSN = SSN;
		this.gender = gender;
		this.contact = contact;
		this.emailAddress = emailAddress;
		this.specialization = specialization;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Appointment> getAppointmentList() {
		return appointmentList;
	}

	public void setAppointmentList(List<Appointment> appointmentList) {
		this.appointmentList = appointmentList;
	}
	/*
	 * public void addAppointment(Appointment appointment) {
	 * appointment.setDoctor(this); appointmentList.add(appointment); }
	 */
}
