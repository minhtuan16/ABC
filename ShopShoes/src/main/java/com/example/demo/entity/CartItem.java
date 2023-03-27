package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "cartitem")
@Data
public class CartItem {

	@Id
	private Integer productId;
	
	private String name;
	
	private double price;
	
	private int qty = 1;
}
