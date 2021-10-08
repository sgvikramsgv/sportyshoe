package com.sportyshoe.model;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name="Orders")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user_name")
	private String username;
	
	@Column(name="order_item")
	private String orderItemName;
	
	@Column(name="order_price")
	private String orderItemPrice;
	
	@Column(name="order_date")
	private LocalDateTime orderDate;
	
	
	

	public Order(String username, String orderItemName, String d) {
		super();
		this.username = username;
		this.orderItemName = orderItemName;
		this.orderItemPrice = d;
	}

	public Order() {
		super();
	}

	public Order(String username, String orderItemName, String orderItemPrice, LocalDateTime orderDate) {
		super();
		this.username = username;
		this.orderItemName = orderItemName;
		this.orderItemPrice = orderItemPrice;
		this.orderDate = orderDate;
	}

	public Order(int id, String username, String orderItemName, String orderItemPrice, LocalDateTime orderDate) {
		super();
		this.id = id;
		this.username = username;
		this.orderItemName = orderItemName;
		this.orderItemPrice = orderItemPrice;
		this.orderDate = orderDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOrderItemName() {
		return orderItemName;
	}

	public void setOrderItemName(String orderItemName) {
		this.orderItemName = orderItemName;
	}

	public String getOrderItemPrice() {
		return orderItemPrice;
	}

	public void setOrderItemPrice(String orderItemPrice) {
		this.orderItemPrice = orderItemPrice;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime localDateTime) {
		this.orderDate = localDateTime;
	}
	
}
