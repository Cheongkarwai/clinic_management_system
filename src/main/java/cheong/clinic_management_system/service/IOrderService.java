package cheong.clinic_management_system.service;

import java.util.List;

import cheong.clinic_management_system.dto.OrderPayerDTO;
import cheong.clinic_management_system.entity.Order;
import cheong.clinic_management_system.entity.OrderDetails;
import cheong.clinic_management_system.model.OrderForm;


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
