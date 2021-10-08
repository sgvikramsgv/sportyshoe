package com.sportyshoe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoe.model.Order;
import com.sportyshoe.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> getAllOrders(){
		return orderRepository.findAll();
	}
	
	public List<Order> getOrdersByUserName(String username){
		
		return orderRepository.findAllByUsername(username);
	}
	
	public void saveOrder(Order order) {
		orderRepository.save(order);
	}
}
