package com.cheong.clinic.order.model;


public class ItemDTO {

	private String id;

	private String name;

	private double price;

	private String description;
	
	public ItemDTO() {
		
	}
	
	public ItemDTO( String name, double price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
	}
	
	public ItemDTO(String id, String name, double price, String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
