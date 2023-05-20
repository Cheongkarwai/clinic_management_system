package cheong.clinic_management_system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="orders",schema="spring_mvc_clinic")
@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "id")
public class Order {
	
	@Id
	@Column(name="id")
	@GeneratedValue(generator = "order_id_generator")
	@GenericGenerator(name="order_id_generator",strategy = "cheong.clinic_management_system.entity.OrderIdGenerator")
	private String id;
	
	@Column(name="total")
	private double total;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="method")
	private String method;
	
	@ManyToOne
	@JoinColumn(name="patient_id",foreignKey = @ForeignKey(name="ORDER_PATIENT_FK"))
	private Patient patient;
	
	@OneToMany(mappedBy="order", orphanRemoval = true,cascade = CascadeType.ALL)
	private List<OrderDetails> orderDetailsList = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name="appointment_id",foreignKey = @ForeignKey(name="ORDER_APPOINTMENT_FK"))
	private Appointment appointment;
	
	@Column(name="status")
	private boolean status;
	
	public Order() {
		
	}
	public Order(String id,double total,String currency,String method) {
		this.id = id;
		this.total = total;
		this.currency = currency;
		this.method = method;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public void addOrderDetails(OrderDetails orderDetails) {
		orderDetails.setOrder(this);
		orderDetailsList.add(orderDetails);
	}
	public List<OrderDetails> getOrderDetailsList(){
		return orderDetailsList;
	}
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

}