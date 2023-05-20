package cheong.clinic_management_system.model;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;

import cheong.clinic_management_system.entity.OrderDetails;



public class OrderForm {
	
	@Valid
	private List<OrderDetails> orderDetails = new LinkedList<>();
	
	@DecimalMin(value="1",message="Consultation Fees must be greater than or equal to 0")
	private double consultationFees;
	
	private int patientId;
	
	private int appointmentId;
	
	public OrderForm() {
		
	}
	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public List<OrderDetails> getOrderDetails(){
		return orderDetails;
	}
	public double getConsultationFees() {
		return consultationFees;
	}
	public void setConsultationFees(double consultationFees) {
		this.consultationFees = consultationFees;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
}
