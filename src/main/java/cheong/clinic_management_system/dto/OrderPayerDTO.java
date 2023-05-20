package cheong.clinic_management_system.dto;

import java.util.List;

public class OrderPayerDTO {
	
	private String orderId;
	private List<OrderDetailsDTO> orderDetails;
	private PayerDTO payer;
	
	public OrderPayerDTO() {
		
	}
	
	public void setOrderId(String id) {
		this.orderId = id;
	}
	
	public String getOrderId() {
		return orderId;
	}
	
	public List<OrderDetailsDTO> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailsDTO> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public PayerDTO getPayer() {
		return payer;
	}

	public void setPayer(PayerDTO payer) {
		this.payer = payer;
	}
	
}
