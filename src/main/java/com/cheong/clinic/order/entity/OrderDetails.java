package com.cheong.clinic.order.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;

import com.cheong.clinic.medicine_dispensing.entity.Medicine;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="orderDetails",schema="spring_mvc_clinic")
@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "id")
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="subtotal")
	private double subtotal;
	
	@Column(name="quantity")
	@DecimalMin(value="1",message="Quantity must be greater than or equal to 1")
	private int quantity;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="order_id",foreignKey = @ForeignKey(name = "ORDERDETAILS_ORDER_FK"))
	private Order order;
	
	@ManyToOne
	@JoinColumn(name="medicine_id",foreignKey = @ForeignKey(name="ORDERDETAILS_MEDICINE_FK"))
	private Medicine medicine;
	
	
	public OrderDetails() {
		
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
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	

	
}
