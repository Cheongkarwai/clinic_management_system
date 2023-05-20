package com.cheong.clinic.order.service;

import java.util.List;

import com.cheong.clinic.order.entity.Order;
import com.cheong.clinic.order.entity.OrderDetails;
import com.cheong.clinic.order.model.OrderForm;
import com.cheong.clinic.payment.model.OrderPayerDTO;


public interface IOrderService {
	
	List<Order> findAll();
	Order findById(String id);
	void save(Order order);
	void saveOrder(OrderForm orderForm);
	void update(Order order);
	void deleteById(String id);
	List<Order> findByPatientUsername(String username);
	List<OrderDetails> findOrderDetailsByOrderId(String orderId);
	Object getOrderTotalByOrderId(String id);
	Object getOrderTaxByOrderId(String id);
	OrderPayerDTO getOrderPayerByOrderId(String id);

}
