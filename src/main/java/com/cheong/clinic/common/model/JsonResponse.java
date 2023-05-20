package com.cheong.clinic.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"statusCode","message"})
public class JsonResponse<T> {

	private T message;
	
	@JsonProperty("status_code")
	private int statusCode;
	
	public JsonResponse(T message,int statusCode) {
		this.message = message;
		this.statusCode = statusCode;
	}
	
	public T getMessage() {
		return message;
	}
	
	public void setMessage(T message) {
		this.message = message;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}