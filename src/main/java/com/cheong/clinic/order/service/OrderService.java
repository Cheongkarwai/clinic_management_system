package com.cheong.clinic.order.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheong.clinic.appointment.repository.AppointmentRepository;
import com.cheong.clinic.order.entity.Order;
import com.cheong.clinic.order.entity.OrderDetails;
import com.cheong.clinic.order.model.ItemDTO;
import com.cheong.clinic.order.model.OrderDetailsDTO;
import com.cheong.clinic.order.model.OrderForm;
import com.cheong.clinic.order.repository.OrderRepository;
import com.cheong.clinic.patient.repository.PatientRepository;
import com.cheong.clinic.payment.model.OrderPayerDTO;
import com.cheong.clinic.payment.model.PayerDTO;


@Service
@Transactional
public class OrderService implements IOrderService {

	private OrderRepository orderRepository;
	private PatientRepository patientRepository;
	private AppointmentRepository appointmentRepository;

	public OrderService(OrderRepository orderRepository, PatientRepository patientRepository,
			AppointmentRepository appointmentRepository) {
		this.orderRepository = orderRepository;
		this.patientRepository = patientRepository;
		this.appointmentRepository = appointmentRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Order> findAll() {

		List<Order> orders = orderRepository.findAll();
		return orders;
	}

	@Override
	@Transactional(readOnly = true)
	public Order findById(String id) {
		Order order = orderRepository.findById(id).orElseThrow();
		return order;
	}

	@Override
	public void save(Order order) {
		orderRepository.save(order);
	}

	@Override
	public void update(Order order) {
		orderRepository.save(order);
	}

	@Override
	public void deleteById(String id) {
		orderRepository.deleteById(id);
	}

	@Override
	public void saveOrder(OrderForm orderForm) {
		Order order = new Order();
		order.setCurrency("RM");
		order.setMethod("PAYPAL");
		order.setStatus(false);

		double total = 0;

		OrderDetails consultationFees = new OrderDetails();
		consultationFees.setName("Consultation Fees");
		consultationFees.setMedicine(null);
		consultationFees.setDescription("This is consultation fees");
		consultationFees.setQuantity(1);
		consultationFees.setSubtotal(orderForm.getConsultationFees());

		order.addOrderDetails(consultationFees);

		for (Iterator<OrderDetails> itr = orderForm.getOrderDetails().iterator(); itr.hasNext();) {
			OrderDetails orderDetails = itr.next();
			orderDetails.setSubtotal(orderDetails.getSubtotal() * orderDetails.getQuantity());
			total += orderDetails.getSubtotal();
			order.addOrderDetails(orderDetails);
		}
		order.setTotal(total + orderForm.getConsultationFees());
		orderRepository.save(order);
		order.setPatient(patientRepository.findById(orderForm.getPatientId()).orElseThrow());
		order.setAppointment(appointmentRepository.findById(orderForm.getAppointmentId()).get());
	}

	@Override
	public List<Order> findByPatientUsername(String username) {

		return orderRepository.findAll()
				.stream()
				.filter(e -> e.getPatient().getUser().getUsername().equals(username))
				.collect(Collectors.toList());
	}

	@Override
	public List<OrderDetails> findOrderDetailsByOrderId(String orderId) {

		List<Order> orders = orderRepository.findAll();

		return orders.stream().filter(order -> order.getId().equals(orderId))
				.flatMap(root -> root.getOrderDetailsList().stream()).collect(Collectors.toList());

	}

	@Override
	public Object getOrderTotalByOrderId(String id) {

		Order order = orderRepository.findById(id).orElseThrow();
		List<OrderDetails> orderDetails = order.getOrderDetailsList();

		double total = 0;
		for (int i = 0; i < orderDetails.size(); i++) {
			total += orderDetails.get(i).getSubtotal();
		}
		return total;
	}

	@Override
	public Object getOrderTaxByOrderId(String id) {

		Order order = orderRepository.findById(id).orElseThrow();
		List<OrderDetails> orderDetails = order.getOrderDetailsList();

		double total = 0;
		for (int i = 0; i < orderDetails.size(); i++) {
			total += orderDetails.get(i).getSubtotal();
		}
		return total;
	}

	@Override
	public OrderPayerDTO getOrderPayerByOrderId(String id) {

		Order order = orderRepository.findById(id).orElseThrow();

		OrderPayerDTO orderPayerDTO = new OrderPayerDTO();

		PayerDTO payer = new PayerDTO(order.getPatient().getId(),order.getPatient().getName(), order.getPatient().getDateOfBirth(),
				order.getPatient().getSSN(), order.getPatient().getContact(), order.getPatient().getEmailAddress(),
				order.getPatient().getAddress(), order.getPatient().getGender());

		List<OrderDetailsDTO> orderDetails = new ArrayList<>();

		for (int i = 0; i < order.getOrderDetailsList().size(); i++) {

			ItemDTO item = null;
			if (order.getOrderDetailsList().get(i).getMedicine() != null) {
				 item = new ItemDTO(order.getOrderDetailsList().get(i).getMedicine().getId(),
						order.getOrderDetailsList().get(i).getMedicine().getName(),
						order.getOrderDetailsList().get(i).getMedicine().getPrice(),
						order.getOrderDetailsList().get(i).getMedicine().getDescription());
			}
			else {
				item = new ItemDTO(order.getOrderDetailsList().get(i).getName(),
						order.getOrderDetailsList().get(i).getSubtotal(),
						order.getOrderDetailsList().get(i).getDescription());
			}
			OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(order.getOrderDetailsList().get(i).getId(),
					order.getOrderDetailsList().get(i).getName(), order.getOrderDetailsList().get(i).getSubtotal(),
					order.getOrderDetailsList().get(i).getQuantity(),
					order.getOrderDetailsList().get(i).getDescription(), item);

			orderDetails.add(orderDetailsDTO);
		}

		orderPayerDTO.setPayer(payer);
		orderPayerDTO.setOrderDetails(orderDetails);
		orderPayerDTO.setOrderId(id);

		return orderPayerDTO;

	}

}
