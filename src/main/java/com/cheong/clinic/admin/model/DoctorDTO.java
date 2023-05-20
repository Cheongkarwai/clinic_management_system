package com.cheong.clinic.admin.model;

import java.time.LocalDate;

import com.cheong.clinic.common.embeddable.Address;


public class DoctorDTO {

	private String id;

	private String name;

	private LocalDate dateOfBirth;
	
	private String SSN;
	
	private String contact;
	
	private String emailAddress;
	
	private Address address;
	
	private char gender;
	
	private String specialization;
	
	public DoctorDTO(String id, String name, LocalDate dateOfBirth, String sSN, String contact, String emailAddress,
			Address address, char gender, String specialization) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		SSN = sSN;
		this.contact = contact;
		this.emailAddress = emailAddress;
		this.address = address;
		this.gender = gender;
		this.specialization = specialization;
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

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}


}
