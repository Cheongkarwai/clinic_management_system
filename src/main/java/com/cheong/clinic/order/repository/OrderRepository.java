package com.cheong.clinic.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheong.clinic.order.entity.Order;


public interface OrderRepository extends JpaRepository<Order, String> {

}
