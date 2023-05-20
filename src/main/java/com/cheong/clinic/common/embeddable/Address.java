package com.cheong.clinic.common.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class Address {
	
	@Column(name="address_line_1" ,nullable=false)
	@NotEmpty(message = "Address line 1 is required")
	private String address_line_1;
	
	@Column(name="address_line_2")
	@NotEmpty(message = "Address line 2 is required")
	private String address_line_2;
	
	@Column(name="postcode",nullable=false)
	@NotEmpty(message = "Postcode is required")
	private String postcode;
	
	@Column(name="state",nullable=false)
	@NotEmpty(message = "State is required")
	private String state;
	
	@Column(name="city",nullable=false)
	@NotEmpty(message = "City is required")
	private String city;
	
	public Address() {
		
	}
	public Address(String address_line_1, String address_line_2, String postcode, String state, String city) {
		this.address_line_1 = address_line_1;
		this.address_line_2 = address_line_2;
		this.postcode = postcode;
		this.state = state;
		this.city = city;
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
