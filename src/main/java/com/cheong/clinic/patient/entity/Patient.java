package com.cheong.clinic.patient.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;
import org.springframework.format.annotation.DateTimeFormat;

import com.cheong.clinic.appointment.entity.Appointment;
import com.cheong.clinic.auth.entity.User;
import com.cheong.clinic.common.embeddable.Address;
import com.cheong.clinic.order.entity.Order;


@Entity
@Table(name="patient")
public class Patient{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="dateOfBirth")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	@Column(name="SSN",nullable=false)
	@NaturalId(mutable = true)
	private String SSN;
	
	@Column(name="contact",nullable=false)
	private String contact;
	
	@Column(name="emailAddress",nullable=false)
	private String emailAddress;
	
	@Embedded
	private Address address;
	
	@Column(name="gender",nullable=false)
	private char gender;
	
	@OneToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="username",unique=true,foreignKey = @ForeignKey(name="PATIENT_USER_FK"))
	private User user;
	
	@OneToMany(mappedBy = "patient", orphanRemoval = true,cascade = CascadeType.ALL)
	private List<Appointment> appointmentList = new ArrayList<>();
	
	@OneToMany(mappedBy = "patient", orphanRemoval = true,cascade = CascadeType.ALL)
	private List<Order> orderList = new ArrayList<>();
	
	public Patient() {
		
	}
	
	public Patient(int id, String name, LocalDate dateOfBirth, String SSN, String contact, String emailAddress,
			Address address, char gender, User user, List<Appointment> appointmentList, List<Order> orderList) {
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.SSN = SSN;
		this.contact = contact;
		this.emailAddress = emailAddress;
		this.address = address;
		this.gender = gender;
		this.user = user;
		this.appointmentList = appointmentList;
		this.orderList = orderList;
	}

	public Patient(String name,LocalDate dateOfBirth, String SSN, String contact, String emailAddress,
			Address address,  char gender) {
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.SSN = SSN;
		this.contact = contact;
		this.emailAddress = emailAddress;
		this.address = address;
		this.gender = gender;
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
	public void setUser(User user) {
		user.setPatient(this);
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void addAppointment(Appointment appointment) {
		appointmentList.add(appointment);
		appointment.setPatient(this);;
	}
	public void addOrder(Order order) {
		orderList.add(order);
		order.setPatient(this);
	}
	public List<Order> getOrderList(){
		return orderList;
	}
	public List<Appointment> getAppointmentList(){
		return appointmentList;
	}

}