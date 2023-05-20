package cheong.clinic_management_system.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="staff")
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="dateOfBirth",nullable=false)
	private LocalDate dateOfBirth;
	
	@Column(name="SSN")
	@NaturalId
	private String SSN;
	
	@Column(name="contact",nullable=false)
	private String contact;
	
	@Column(name="emailAddress",nullable=false)
	private String emailAddress;
	
	@Embedded
	private Address address;
	
	@Column(name="gender",nullable = false)
	private char gender;
	
	@Column(name="education",nullable=false)
	private String education;
	
	@Column(name="profession",nullable=false)
	private String profession;
	
	@Column(name="maritalstatus",nullable=false)
	private String maritalStatus;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="username",unique = true,foreignKey=@ForeignKey(name="STAFF_USER_PK"))
	private User user;

	public Staff() {

	}

	public Staff(String name, LocalDate dateOfBirth, String SSN, String contact, String emailAddress,
			Address address, char gender, String education, String profession, String maritalStatus) {
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.SSN = SSN;
		this.contact = contact;
		this.emailAddress = emailAddress;
		this.address = address;
		this.gender = gender;
		this.education = education;
		this.profession = profession;
		this.maritalStatus = maritalStatus;
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

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String SSN) {
		this.SSN = SSN;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}



	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}