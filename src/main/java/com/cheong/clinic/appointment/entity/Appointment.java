package com.cheong.clinic.appointment.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.cheong.clinic.admin.entity.Doctor;
import com.cheong.clinic.order.entity.Order;
import com.cheong.clinic.patient.entity.Patient;

@NamedQueries({
	@NamedQuery(name="Appointment.findEndDateByDesc",
			query = "FROM Appointment ORDER BY appointmentEndDateTime DESC"),
})
//Entity name is implicitly created, however we can explicitly name it so it can be 
//used in HQL
@Entity(name="Appointment")
@Table(name="Appointment",
	schema="spring_mvc_clinic")
//@Access(AccessType.FIELD)
//Access strategy will be implicitly specified when annotate @Id 
public class Appointment{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	//It will point to the Patient Entity id using referencedColumnName 
	@JoinColumn(name="patient_id",foreignKey = @ForeignKey(name="APPOINTMENT_PATIENT_FK"))
	private Patient patient;
	
	@Column(name="appointmentStartDateTime")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime appointmentStartDateTime;
	
	@Column(name="appointmentEndDateTime")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime appointmentEndDateTime;
	
	@ManyToOne
	@JoinColumn(name="doctor_id",foreignKey = @ForeignKey(name="APPOINTMENT_DOCTOR_FK"))
	private Doctor doctor;
	
	@Column(name="dateTimeCreated")
	private LocalDateTime dateTimeCreated;
	
	@Column(name="status")
	private boolean status;
	
	@OneToOne(mappedBy="appointment",cascade = CascadeType.ALL)
	private Order order;

	public Appointment() {

	}

	public Appointment(Patient patient, LocalDateTime appointmentStartDateTime,LocalDateTime appointmentEndDateTime, 
			Doctor doctor, LocalDateTime dateTimeCreated,boolean status) {
		this.patient = patient;
		this.appointmentStartDateTime = appointmentStartDateTime;
		this.appointmentEndDateTime = appointmentEndDateTime;
		this.doctor = doctor;
		this.dateTimeCreated = dateTimeCreated;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		
		this.doctor = doctor;
	}

	public LocalDateTime getDateTimeCreated() {
		return dateTimeCreated;
	}

	public void setDateTimeCreated(LocalDateTime dateTimeCreated) {
		this.dateTimeCreated = dateTimeCreated;
	}


	public LocalDateTime getAppointmentStartDateTime() {
		return appointmentStartDateTime;
	}

	public void setAppointmentStartDateTime(LocalDateTime appointmentStartDateTime) {
		this.appointmentStartDateTime = appointmentStartDateTime;
	}

	public LocalDateTime getAppointmentEndDateTime() {
		return appointmentEndDateTime;
	}

	public void setAppointmentEndDateTime(LocalDateTime appointmentEndDateTime) {
		this.appointmentEndDateTime = appointmentEndDateTime;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		order.setAppointment(this);
		this.order = order;
	}
}