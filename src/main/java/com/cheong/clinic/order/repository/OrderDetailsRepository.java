package com.cheong.clinic.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheong.clinic.order.entity.OrderDetails;


public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>{


}
