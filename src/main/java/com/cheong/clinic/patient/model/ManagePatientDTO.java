package com.cheong.clinic.patient.model;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class ManagePatientDTO {

	private int id;
	
	private String name;
	
	private String SSN;

	private String contact;

	
	public ManagePatientDTO(int id,String name, String SSN, String contact) {
		this.id = id;
		this.name = name;
		this.SSN = SSN;
		this.contact = contact;
	}
	
	public ManagePatientDTO() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
