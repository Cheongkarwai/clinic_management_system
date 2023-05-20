package com.cheong.clinic.patient.model;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.cheong.clinic.common.validator.Gender;



public class PatientForm {

	public int id;
	
	@NotEmpty(message = "Name is required")
	private String name;
	
	@NotNull(message = "Date of birth is required")
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate dateOfBirth;
	
	@NotEmpty(message = "SSN is required")
	private String SSN;
	
	@Gender(message = "Gender is required")
	private char gender;
	
	@NotEmpty(message = "Contact is required")
	private String contact;
	
	@NotEmpty(message = "Email address is required")
	private String emailAddress;
	
	@NotEmpty(message = "Address line 1 is required")
	private String address_line_1;
	
	@NotEmpty(message = "Address line 2 is required")
	private String address_line_2;
	
	@NotEmpty(message = "Postcode is required")
	private String postcode;
	
	@NotEmpty(message = "State is required")
	private String state;
	
	@NotEmpty(message = "City is required")
	private String city;
	
	
	public PatientForm() {
		
	}
	public PatientForm(int id, String name, LocalDate dateOfBirth, String ssn, char gender, String contact, String emailAddress,
			String address_line_1, String address_line_2, String postcode, String state, String city) {
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.SSN = ssn;
		this.gender = gender;
		this.contact = contact;
		this.emailAddress = emailAddress;
		this.address_line_1 = address_line_1;
		this.address_line_2 = address_line_2;
		this.postcode = postcode;
		this.state = state;
		this.city = city;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
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
	public void setSSN(String sSN) {
		SSN = sSN;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
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
	public String getAddress_line_1() {
		return address_line_1;
	}
	public void setAddress_line_1(String address_line_1) {
		this.address_line_1 = address_line_1;
	}
	public String getAddress_line_2() {
		return address_line_2;
	}
	public void setAddress_line_2(String address_line_2) {
		this.address_line_2 = address_line_2;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
